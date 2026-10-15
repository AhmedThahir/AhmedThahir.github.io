#include <stdio.h>

int main()
{
	int a[3][3];
	
	for(int i=0; i<3; i++)
		for(int j=0; j<3; j++)
			a[i][j] = (i+j+1)*10;

	for(int i=0; i<3; i++)
	{
		for(int j=0; j<3; j++)
			printf("%d\t", a[i][j]);
		printf("\n");
	}

	int rsum, csum;
	for(int i=0; i<3; i++)	
	{
		rsum = 0;
		for(int j=0; j<3; j++)
			rsum+=a[i][j];
		printf("\nSum of row %d: %d", i,rsum);
	}

	printf("\n\n");

	for(int j=0; j<3; j++)
	{
		csum=0;
		for(int i=0; i<3; i++)
			csum+=a[i][j];
		printf("\nSum of col %d: %d", j, csum);
	}
	
	return 0;
}
