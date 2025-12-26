#include <stdio.h>

int main()
{
	int n = 5;
	int a[n], b[n];
	
	for (int i = 0; i<n; i++)
	{
	           printf("Element %d of array A: ", i+1 ); scanf(" %d", &a[i]);
	}
	printf("\n\n");
	for(int i = 0; i<n; i++)
	{
		printf("Element %d of array B: ", i+1 ); scanf(" %d", &b[i]);
	}
		 
	int flag = 1;

	for (int i = 0; i<n; i++)
		if(a[i] != b[i])
		{
			flag = 0;
			break;
		}

	if(flag==1)
		printf("\n\nIdentical\n\n");
	else
		printf("\n\nNot identical\n\n");


	return 0;
}
