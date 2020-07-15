function addSymbol(last, position) {
    if (position > 14) {
        return 1;
    }
    let sum = 0;
    if (last === 0) {
        sum += addSymbol(position, position + 2);
    }
    sum += addSymbol(0, position + 2);
    return sum;
}

let res = addSymbol(0, 2);
// console.log(res);
console.log(2**8 * res);
