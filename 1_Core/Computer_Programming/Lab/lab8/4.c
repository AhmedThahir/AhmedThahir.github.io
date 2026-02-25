#include <stdio.h>

int main()
{
	int n;
	printf("\nNumber: "); scanf(" %d", &n);

	int x, sum, rev;

	//sum
	x=n, sum = 0;
	do
	{	
		int ld=x%10;
		sum+=ld;
		x/=10;
	} while(x!=0);

	//rev
	x=sum, rev = 0;
	do
	{
		int ld= x%10;
		rev = (rev*10) + ld;
		x/=10;
	} while(x!=0);

	int product = sum*rev;
		
	printf("\n%d is ", n);
	if(product != n)
		printf("not ");
	printf("magic number\n\n");

	
	return 0;
}
