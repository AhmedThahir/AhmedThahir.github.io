#include <stdio.h>

void func(int *p1, int *p2)
{
	char s; printf("Enter operation: "); scanf(" %c", &s);
	
	switch(s)
	{
		case '+': printf("Sum is %d", *p1+*p2); break;
		case '-': printf("Difference is %d", *p1-*p2); break;
		case '*': printf("Product is %d", *p1 * *p2); break;
		case '/': printf("Quotient is %d", *p1 / *p2); break;
		default: printf("Wrong Option");
	}


}

int main()
{
	int a = 5, b = 3;
	
	func(&a, &b);

	return 0;
}
