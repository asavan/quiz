const arr = [];
const a1 = 2;
const a2 = 2019;
arr.push(a1);
arr.push(a2);
for (let i = 3; i <= 2018; ++i) {
    const next = (arr[i-2] + 1) / arr[i-3];
    arr.push(next);
}
console.log(arr[2017]);
