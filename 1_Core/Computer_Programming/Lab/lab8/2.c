#include <stdio.h>

int main()
{

	
	printf("y \t x \t\t i\n");
	for(int y=1;y<=6; y++)
	{
		for(float x=5.5; x<=12.5; x+=0.5)
		{
			float i = 2 +(y+ 0.5*x);
			//printf("test");
			printf("%d \t %f \t %f\n", y, x, i);
		}
		printf("\n\n");
	}
	return 0;
}
