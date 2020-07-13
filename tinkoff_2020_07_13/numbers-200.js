const arr = [];
arr.push(2);
arr.push(2019);
for (let i = 3; i <= 2018; ++i) {
    const next = (arr[i - 2] + 1) / arr[i - 3];
    arr.push(next);
}
console.log(arr[2017]);
