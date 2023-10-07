let n = 0;
while (++n < 5) {
	setTimeout(() => console.log(n), 10 + n);
}
