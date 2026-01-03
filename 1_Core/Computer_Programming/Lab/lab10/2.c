#include <stdio.h>

int main()
{
	int a[3][3];

	for(int i=0; i<3; i++)
		for(int j=0; j<3; j++)
			a[i][j]=(i+j+1)*10;
	for(int i=0; i<3; i++)
	{
		for(int j=0; j<3; j++)
			printf("%d\t", a[i][j] );
		printf("\n");
	}


	int msum=0, osum=0;
	for(int i=0; i<3; i++)
	{
		for(int j=0; j<3; j++)
		{
			if(i==j)
				msum+=a[i][j];	
			if(i+j==3-1)
				osum+=a[i][j];
		}
	}

	printf("\n\nMain Diagonal Sum: %d \nOpposite Diagonal Sum: %d", msum, osum);

	return 0;
}
