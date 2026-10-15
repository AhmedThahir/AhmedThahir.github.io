#include <stdio.h>

int main()
{
	float r, a, p;
	const float pi = 3.14;
	
	printf("\n Enter the radius of the circle \n");
	scanf("%f", &r);

	a = pi * r * r;
	p = 2 * pi * r;
	
	printf("The area is: %f \n The circumference/perimeter is: %f", a, p);

	return 0;

}
