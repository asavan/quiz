// https://nplus1.ru/material/2018/01/31/tinkoff-analytics

/*
Перед Аленой лежит запись 1234567890 = 100. Ей можно между цифрами левой части вставлять символы +, -, *, /,
 чтобы найти число всех способов получить верное равенство.
 Помогите Алене справиться с задачей (можете даже написать вспомогательную программу).
 */

const input = "1234567890";
const chars = ["", "-", "*", "+", "/"];
let count = 0;
let totalCount = 0;
const len = input.length;

const countValue = function (value, i, isMinus) {
    if (i === len) {
        ++totalCount;
        if (eval(value) === 100) {
            ++count;
            if (isMinus) {
                ++count;
            }
            console.log(value);
        }
        return;
    }

    let lastchar = 5;
    if (i === 0) {
        lastchar = 1;
    } else if (i === len - 1) {
        lastchar = 3;
    }
    for (let ch = 0; ch < lastchar; ++ch) {
        countValue(value + chars[ch] + input[i], i + 1, ch === 1);
    }
};

console.time("time");
countValue("", 0, false);
console.timeEnd("time");

console.log("Count ", count, totalCount);
