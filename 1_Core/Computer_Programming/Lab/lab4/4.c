#include <stdio.h>

int main()
{	
	int num, x, ld, sum=0;
	
	printf("\n 4-digit number: ");
	scanf("%d", &num);
	x = num;

/*
	while(x!=0)
	{
		ld = x%10;
		sum += ld;
		x/=10;
	}
*/
	int a,b,c,d;
	a = x%10; x/=10;
	b = x%10; x/=10;
	c = x%10; x/=10;
	d = x%10; x/=10;

	sum += a + b + c + d;

	printf("\n The sum of the digits = %d", sum);

	return 0;
}
