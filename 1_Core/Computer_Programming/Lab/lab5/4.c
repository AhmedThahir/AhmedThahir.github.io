#include <stdio.h>

int main()
{
	float a, b, c, d, e,
		per;

	printf("Subject 1 Marks \n"); scanf("%f", &a);
	printf("Subject 2 Marks \n"); scanf("%f", &b);
	printf("Subject 3 Marks \n"); scanf("%f", &c);
	printf("Subject 4 Marks \n"); scanf("%f", &d);
	printf("Subject 5 Marks \n"); scanf("%f", &e);
	
	per = (a+b+c+d+e)/5;
	

	printf("\nThe division is: ");
	if(per>80)
		printf("Distinction");
	else if (per>=60)
		printf("First");
	else if (per>=45)
		printf("Second");
	else
		printf("Fail");



	return 0;
}
