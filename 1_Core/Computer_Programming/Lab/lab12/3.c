#include <stdio.h>

int main()
{
	int a[5]= {10, 20, 30, 40, 50}, b[5]={0};
	int *p = a;

	for(int i = 0; i<5; i++)
		b[i] = *(p+i);

	for(int i = 0; i<5; i++)
		printf("%d\n", b[i]);

	return 0;
}
