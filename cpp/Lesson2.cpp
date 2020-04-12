// my test good values
// value                            // result
// 10 1 2 3 1 2 3 1 1 1 1           // 4 7
// 10 1 2 3 4 3 3 1 2 3 4           // 4 1
// 10 1.5 2 3 4.7 3 3 1 2 3 4       // 4 1
// 3 1 2 3 1 2 3 1 1 1 1            // 3 1
// 3 10 20 30 40 50                 // 3 1
// 5 5 4 3 2 1                      // 0
// my test error values
// 10.1 1 2 3 1 2 3 1 1 1 1         // [error]
// 10   1 2 3 1 2 3 1 1 1 1
// 10 1 2   3 1 2 3 1 1 1 1
// 10 1 2 3
// 3 10 20 30 40 50  zxczxc

#include <stdio.h>

int main2(void)
{
	int N;
	double prev;
	int maxN = 1;
	int maxLength = 1;
	int currentN = 1;
	int lenght = 1;
	int result = scanf("%lf", &prev);
	if (result != 1) {
		printf("[error]");
		return 0;
	}
	N = prev;
	if (prev != N)
	{
		printf("[error]");
		return 0;
	}
	if (N < 2) {
		printf("[error]");
		return 0;
	}
	
	for (int i = 0; i < N; ++i) {
		double temp;
		int result = scanf("%lf", &temp);
		if (result != 1) {
			printf("[error]");
			return 0;
		}
		
		if (i > 0) {
			if (prev <= temp) {
				++lenght;
				if (lenght > maxLength) {
					maxLength = lenght;
					maxN = currentN;
				}
			}
			else {
				currentN = i+1;
				lenght = 1;
			}
		}
		prev = temp;
	}
	if (maxLength == 1) {
		printf("0");
	}
	else {
		printf("%d %d", maxLength, maxN);
	}
	return 0;
}