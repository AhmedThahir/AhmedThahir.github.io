#include <stdio.h>

int main()
{
	int n = 3;
	int a[n][n], b[n][n], c[n][n], d[n][n];

	for (int i = 0; i<n; i++)
		for(int j=0; j<n; j++)
		{
			printf("Element of array A in row %d col %d\t", i+1, j+1); 
			scanf(" %d", &a[i][j]);
		}

	for (int i = 0; i<n; i++)
		for (int j = 0; j<n; j++)
		{
			printf("Element of array B in row %d col %d\t", i+1, j+1);
			scanf(" %d", &b[i][j]);
		}

	for (int i = 0; i<n; i++)
		for (int j=0; j<n; j++)
			c[i][j] = a[i][j] + b[i][j];
	
	printf("\n\nSum\n");
	for (int i = 0; i<n; i++)
	{
		for (int j=0; j<n; j++)
			printf("%d\t", c[i][j]);
		printf("\n");
	}
	
	for(int i = 0; i<n; i++)
		for (int j = 0; j<n; j++)
			d[i][j] = a[i][j] - b[i][j];

	printf("\n\nDifference\n");
	for(int i = 0; i<n; i++)
	{
		for (int j =0; j<n; j++)
			printf("%d\t", d[i][j]);
		printf("\n");
	}

	return 0;
}
