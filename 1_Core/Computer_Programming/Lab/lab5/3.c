#include <stdio.h>

int main()
{
	float x, y;

	printf("X-coordinate: "); scanf("%f", &x);
	printf("\nY-coordinate: "); scanf("%f", &y);


	if(x==0 && y==0)
		printf("Origin");
	else if(x==0)
		printf("Y-axis");
	else if(y==0)
		printf("X-axis");
	else 
	{
		int q=0;
	
		if(x>0 && y>0)
			q=1;
		else if(x<0 && y>0)
			q=2;
		else if(x<0 && y<0)
			q=3;
		else
			q=4;
		
		printf("The quadrant is Q%d", q);
	}
	printf("\n");

	return 0;
}
