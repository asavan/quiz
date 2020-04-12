#include <stdio.h>
#include <math.h>

typedef unsigned long long my_type;

my_type f(double x) {
	my_type m = (my_type)sqrt(x*2.0);
	if (((m + 1)*m / 2) == x) {
		return m;
	}
	return 0;
}

int main1(void)
{
	double temp;
	double d = 9223372036854775807;
	int result = scanf("%lf", &temp);
	if (result != 1) {
		printf("0");
		return 0;
	}
	if (temp <= 0 || temp > d) {
		printf("0");
		return 0;
	}
	// printf("%llu", temp);
	printf("%llu", f(temp));
	return 0;
}