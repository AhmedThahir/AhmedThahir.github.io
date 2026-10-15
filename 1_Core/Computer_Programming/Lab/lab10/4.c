#include <stdio.h>

int main()
{
	int a[2][3][3];

	for(int i=0; i<2; i++)
	{	
		printf("\n\nDimension %d\n", i);
		for(int j=0; j<3; j++)
			for(int k=0; k<3; k++)
			{
				printf("\nElement (%d,%d,%d): ", i,j,k);
				scanf("%d", &a[i][j][k]);
			}
	}
	
	printf("\n\n");
	for(int i=0; i<2; i++)
	{
		printf("\n\nDimension %d\n", i);
		for(int j=0; j<3; j++)
		{
			for(int k=0; k<3; k++)
			{
				printf("%d\t", a[i][j][k]);
			}
			printf("\n");
		}
	}


	return 0;
}
