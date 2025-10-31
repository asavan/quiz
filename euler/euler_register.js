import test from "node:test";
import assert from "node:assert/strict";

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

function sumOddSquaresNaive(end) {
    let sum = 0;
    for (let i = 1; i <= end; i+=2) {
        sum += i * i;
    }
    return sum;
}

function sumOddSquares(end) {
    let sum = BigInt(0);
    for (let i = 1; i <= end; i+=2) {
        const mult = BigInt(i);
        sum += mult * mult;
    }
    return sum;
}

function sumSquares(n) {
    return n * (n+1) * (2*n+1) / 6;
}

function sumOddSquaresFast(end) {
    const half = Math.floor(end/2);
    const extra = sumSquares(half);
    return sumSquares(end) - 4 * extra;
}

test("euler login", () => {
    assert.equal(sumOddSquaresNaive(5), 35);
    assert.equal(sumOddSquares(5), BigInt(35));
    assert.equal(sumOddSquares(752000), BigInt(70876501333208000));
    assert.equal(sumOddSquaresFast(5), 35);
    // first fail at 378077
    assert.equal(sumOddSquaresFast(752000), 70876501333208000);
});
