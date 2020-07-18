// Наименьшее двузначное число, у которого сумма цифр не меняется при умножении на 2,3,4,5

function sum(value) {
    let sum1 = 0;

    while (value) {
        sum1 += value % 10;
        value = Math.floor(value / 10);
    }
    return sum1;
}

const reducer = (accumulator, currentValue, multiplierN) => {
    if (accumulator < 0) {
        return accumulator;
    }
    const res = sum(currentValue * multiplierN);
    if (accumulator === 0 || accumulator === res) {
        return res;
    }
    return -1;
};

function main() {
    const mult = [2, 3, 4, 5];
    let i = 0;
    for (i = 10; i <= 99; ++i) {
        const res = mult.reduce((accumulator, currentValue) => reducer(accumulator, currentValue, i), 0);
        if (res > 0) {
            break;
        }
    }
    console.log(i);
}

main();
