import test from "node:test";
import assert from "node:assert/strict";
import * as readline from 'node:readline/promises';
import * as fs from 'node:fs';

const ranks = "##23456789TJQKA";
const suits = "#CSDH";

const POWER_HI_CARD = 1;
const POWER_PAIR = 2;
const POWER_TWO_PAIRS = 3;
const POWER_TREE = 4;
const POWER_STRAIGHT = 5;
const POWER_FLUSH = 6;
const POWER_FULL_HOUSE = 7;
const POWER_FOUR = 8;
const POWER_STRAIGHT_FLUSH = 9;

const ASE_RANK = 14;

function assertE(a, b) {
    assert.equal(a, b);
}

function assertT(c) {
    assert.equal(c, true);
}

function readCard(str) {
    assertE(str.length, 2);
    const rank = ranks.indexOf(str[0]);
    assertT(rank > 1);
    const suit = suits.indexOf(str[1]);
    assertT(suit > 0);
    return {
        rank,
        suit
    }
}

function readHand(str, from, to) {
    const cards = [];
    for (let i = from; i < to; i += 3) {
        const cardStr = str.substring(i, i+2);
        const card = readCard(cardStr);
        cards.push(card);
    }
    assertE(cards.length, 5);
    return cards;
}

function getStraight(cards) {
    assertE(cards.length, 5);
    let curRank = cards[0];
    let hiRank = curRank;
    let startIndex = 1;
    if (curRank === ASE_RANK) {
        startIndex = 2;
        if (cards[1] === 5) {
            hiRank = 5;
            // console.log("5 street");
        } else if (cards[1] !== curRank - 1) {
            return 0;
        }
    }
    for (let i = startIndex; i < 5; ++i) {
        const cur = cards[i];
        const prev = cards[i-1];
        if (cur !== prev - 1) {
            return 0;
        }
    }
    return hiRank;
}

function getFlush(suits) {
    const suitSet = new Set(suits);
    if (suitSet.size === 1) {
        return suits[0];
    }
    return "";
}

function handToPower(cards) {
    assertE(cards.length, 5);
    const mapRanks = new Map();
    for (const card of cards) {
        const arr = mapRanks.get(card.rank);
        if (!arr) {
            mapRanks.set(card.rank, [card.suit]);
        } else {
            arr.push(card.suit);
        }
    }
    if (mapRanks.size === 5) {
        cards.sort((c1, c2) => {
            return c2.rank - c1.rank;
        });
        const cardRanks = cards.map(c => c.rank);
        const strHi = getStraight(cardRanks);
        const suits = cards.map(c => c.suit);
        const hasFlush = getFlush(suits);

        if (strHi && hasFlush) {
            return {
                power: POWER_STRAIGHT_FLUSH,
                hi: strHi,
                suit: hasFlush
            }
        }
        if (hasFlush) {
            return {
                power: POWER_FLUSH,
                hi: cards[0].rank,
                suit: hasFlush
            }
        }
        if (strHi) {
            return {
                power: POWER_STRAIGHT,
                hi: strHi
            }
        }
        return {
            power: POWER_HI_CARD,
            hi: POWER_HI_CARD,
            low: POWER_HI_CARD,
            rest: cardRanks
        }
    }
    if (mapRanks.size === 1) {
        assertT(false);
        return {
            power: 0,
            hi: 0,
            low: 0,
            rest: []
        }
    }
    if (mapRanks.size === 2) {
        let hi = 0;
        let low = 0;
        let biggest = 0;
        for (const [key, value] of mapRanks.entries()) {
            if (value.length >= 3) {
                biggest = value.length;
                hi = key;
            }
            if (value.length <= 2) {
                low = key;
            }
        }
        let power = 0;
        if (biggest === 4) {
            power = POWER_FOUR;
        }
        if (biggest === 3) {
            power = POWER_FULL_HOUSE;
        }
        assertT(power > 0);
        return {
            power,
            hi,
            low
        };
    }
    if (mapRanks.size === 4) {
        let hi = 0;
        const rest = [];
        for (const [key, value] of mapRanks.entries()) {
            if (value.length === 2) {
                hi = key;
            } else if (value.length === 1) {
                rest.push(key);
            } else {
                assertT(false);
            }
        }
        rest.sort((a, b) => b-a);
        return {
            power: POWER_PAIR,
            hi,
            low: hi,
            rest
        };
    }
    if (mapRanks.size === 3) {
        let hi = 0;
        let low = 0;
        let power = POWER_TWO_PAIRS;
        let lows = [];
        const rest = [];
        for (const [key, value] of mapRanks.entries()) {
            if (value.length === 3) {
                power = POWER_TREE;
                hi = key;
            } else if (value.length === 2) {
                lows.push(key);
            } else if (value.length === 1) {
                rest.push(key);
            } else {
                assertT(false);
            }
        }
        if (hi) {
            assertE(lows.length, 0);
            low = hi;
        } else {
            assertE(lows.length, 2);
            hi = Math.max(lows[0], lows[1]);
            low = Math.min(lows[0], lows[1]);
        }
        rest.sort((a, b) => b-a);

        return {
            power,
            hi,
            low,
            rest
        };
    }

    assertT(false);
}

const WIN = 1;
const LOOSE = -1;
const DRAW = 0;

function getWinner(hand1, hand2) {
    const power1 = handToPower(hand1);
    const power2 = handToPower(hand2);
    if (power1.power > power2.power) {
        return WIN;
    }
    if (power1.power < power2.power) {
        return LOOSE;
    }
    if (power1.hi > power2.hi) {
        return WIN;
    }
    if (power1.hi < power2.hi) {
        return LOOSE;
    }
    if (power1.low > power2.low) {
        return WIN;
    }
    if (power1.low < power2.low) {
        return LOOSE;
    }
    assertE(power1.rest.length, power2.rest.length);
    for (let i = 0; i < power1.rest.length; ++i) {
        const c1 = power1.rest[i];
        const c2 = power2.rest[i];
        if (c1 > c2) {
            return WIN;
        }
        if (c1 < c2) {
            return LOOSE;
        }
    }
    return DRAW;
}

function getWinnerByStr(str) {
    const hand1 = readHand(str, 0, 14);
    const hand2 = readHand(str, 15, str.length);
    let result = getWinner(hand1, hand2);
    assertT(result !== DRAW);
    if (result === LOOSE) {
        result = 0;
    }
    return result;
}

test("card", () => {
    const card = readCard("AS");
    assert.equal(card.rank, ASE_RANK);
    assert.equal(card.suit, 2);
});

test("hand", () => {
    const handStr = "QH 6C TC 6H TD";
    const hand = readHand(handStr, 0, handStr.length);
    assert.equal(handStr.length, 14);
    assert.equal(hand.length, 5);
    assert.equal(hand[0].rank, 12);
});

test("hand2", () => {
    const handStr = "QH 6C TC 6H TD 4C 9D 2H QC 8H";
    assert.equal(handStr.indexOf("4"), 15);
    const hand = readHand(handStr, 15, handStr.length);
    assert.equal(hand.length, 5);
    assert.equal(hand[0].rank, 4);
});

test("straight", () => {
    assert.equal(getStraight([14, 13, 12, 11, 10]), 14);
    assert.equal(getStraight([14, 5, 4, 3, 2]), 5);
    assert.equal(getStraight([14, 5, 4, 4, 2]), 0);
    assert.equal(getStraight([14, 13, 4, 3, 2]), 0);
    assert.equal(getStraight([6, 5, 4, 3, 2]), 6);
    assert.equal(getStraight([7, 5, 4, 3, 2]), 0);
});

test("power", () => {
    const handStr = "QH 6C TC 6H TD";
    const hand = readHand(handStr, 0, handStr.length);
    assert.equal(handStr.length, 14);
    assert.equal(hand.length, 5);
    assert.equal(hand[0].rank, 12);

    const power = handToPower(hand);
    assert.equal(power.power, POWER_TWO_PAIRS);
    assert.equal(power.hi, 10);
    assert.equal(power.low, 6);
});

test("fullData", async ()=> {
    const fileStream = fs.createReadStream('./0054_poker.txt');
    const rl = readline.createInterface({
        input: fileStream,
        crlfDelay: Infinity // Handle both \r\n and \n line endings
    });
    let count = 0;
    let lines = 0;
    for await (const line of rl) {
        if (line.length === 0) {
            console.log("Empty line");
            continue;
        }
        ++lines;
        count += getWinnerByStr(line);
        // Perform asynchronous operations with each line
    }
    console.log(lines, count);
});

/*
A number is a perfect square, or a square number, if it is the square of a positive integer.
For example,
 is a square number because
; it is also an odd square.

The first 5 square numbers are:
, and the sum of the odd squares is
.

Among the first 752 thousand square numbers, what is the sum of all the odd squares?
 */

function sumSquares(end) {
    let sum = 0;
    for (let i = 1; i <= end; ++i) {
        if (i % 2 !== 0) {
            sum += i*i;
        }
    }
    return sum;
}

test("euler login", () => {
    assert.equal(sumSquares(5), 35);
    console.log(sumSquares(752000));
});
