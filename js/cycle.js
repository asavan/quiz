// https://www.youtube.com/watch?v=m66ltXE4DVg

function same(arr1, arr2, len) {
    for (let i = 0; i < len; ++i) {
        if (arr1[i] !== arr2[i]) {
            return false;
        }
    }
    return true;
}

function next(arr) {
    const res = [];
    let sum = arr[0];
    for (let i = 1; i < arr.length; ++i) {
        sum += arr[i];
        res.push(arr[i]);
    }
    res.push(sum % 10);
    return res;
}

function main() {
    const begin = [1, 2, 3, 4];
    let count = 1;
    let n = next(begin);
    while (!same(begin, n, 4)) {
        console.log(n);
        n = next(n);
        ++count;
    }
    console.log(n);
    console.log(count);
}

main();
