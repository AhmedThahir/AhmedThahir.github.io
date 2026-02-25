#include <stdio.h>

int main()
{
	float basic, hra, allow,
		sal;
	
	printf("\n Basic salary: ");
	scanf("%f", &basic);

	printf("\n Allowance: ");
	scanf("%f", &allow);
	
	hra = (40.0/100.0) * basic;
// hra = 0.4 * basic;

	// hra = 1000;
	sal = basic + hra + allow;
	
	printf("The total salary of the employee is: %f", sal);

	return 0;
}
