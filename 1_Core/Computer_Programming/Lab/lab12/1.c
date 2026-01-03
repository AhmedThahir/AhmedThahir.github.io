#include <stdio.h>

int main()
{
	int a = 5, b = 3;
	int *p1, *p2;
	p1= &a, p2 = &b;

	//printf("%d", *p1);

	printf("Before: a=%d b=%d \n", a, b);

	*p1 = *p1 + *p2;
	*p2 = *p1 - *p2;
	*p1 = *p1 - *p2;

	printf("After: a=%d b=%d", a, b);
	return 0;
}
