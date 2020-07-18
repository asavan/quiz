let i = 0;
for (i = 100; i < 1000000; ++i) {
    if (Math.floor(Math.sqrt(i)) === Math.floor(Math.sqrt(i+101))) {
        break;
    }
}// console.log(res);
console.log(i);
