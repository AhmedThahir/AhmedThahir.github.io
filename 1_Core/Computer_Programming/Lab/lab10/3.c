#include <stdio.h>

int main()
{
	int a[4][4], b[4][4];

	
	printf("Array A\n");
	for(int i=0; i<4; i++)
		for(int j=0; j<4; j++)
		{
			printf("\nElement (%d,%d): ", i, j);
			scanf(" %d",&a[i][j]);
		}

	printf("\n\nArray B\n");
	for(int i=0; i<4; i++)
		for(int j=0; j<4; j++)
		{
			printf("\nElement (%d, %d): ", i, j);
			scanf(" %d", &b[i][j]);
		}

	int sum[4][4], dif[4][4];
	for(int i=0; i<4; i++)
		for(int j=0; j<4; j++)
			sum[i][j]=a[i][j]+b[i][j];
	for(int i=0; i<4; i++)
	        for(int j=0; j<4; j++)
		        dif[i][j]=a[i][j]-b[i][j];
	
	printf("\n\n Sum\n");
	for(int i=0; i<4; i++)
	{
		for(int j=0; j<4; j++)
	               printf("%d\t", sum[i][j] );
		printf("\n");
	}

	printf("\n\n Difference\n");
	for(int i=0; i<4; i++)
	{
		for(int j=0; j<4; j++)
			printf("%d\t", dif[i][j] );
		printf("\n");
	}

	return 0;
}
