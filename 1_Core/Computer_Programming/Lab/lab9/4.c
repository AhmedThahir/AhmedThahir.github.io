#include <stdio.h>

int main()
{
	int a[2][3][2];
	
	for(int i=0; i<2; i++)
		for(int j = 0; j<3; j++)
			for(int k=0; k<2; k++)
			{
				printf("Element of (%d,%d,%d)\t", i, j, k);
				scanf(" %d", &a[i][j][k]);
			}	
				
	for (int i=0; i<2; i++)
	{
		printf("Dimension %d\n", i);
		for(int j=0; j<3; j++)
		{
			for(int k=0; k<2; k++)
			{
				printf("%d\t", a[i][j][k]);
			}
			printf("\n\n");
		}

		printf("\n\n\n");
	}

	return 0;
	
}
