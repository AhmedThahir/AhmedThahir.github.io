#include <stdio.h>

int main()
{
	int a, b;
	printf("a = "); scanf("%d", &a);
	printf("\nb = "); scanf("%d", &b);
	
	/*
	
	//3 bucket system
	int c;
	c = a; a = b; b = c;
	
	*/

	//IDK
	a = a+b; b = a-b; a = a-b;


	printf("a = %d and b = %d", a, b);
	return 0;
}
