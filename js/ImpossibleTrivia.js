// https://youtu.be/iQMSFaoJ2QM?t=969
let i;
for (i = 0; i < 3; i++) {
    const log = () => {
        console.log(i);
    }
    setTimeout(log, 100);
}

for (let i = 0; i < 3; i++) {
    const log = () => {
        console.log(i);
    }
    setTimeout(log, 100);
}

// https://youtu.be/iQMSFaoJ2QM?t=1011
const expression = 1 + 4*2 + 7 - '6' + '4'*5 + '3' - '12';
console.log(expression);
