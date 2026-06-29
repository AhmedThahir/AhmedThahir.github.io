#include <stdio.h>

int main()
{
	int n;
	printf("Pattern size: "); scanf(" %d", &n);

	for(int i = 1; i<=n; i++)
	{
		printf("\n");
		for(int j=i; j>=1; j--)
			printf("%d", j);
	}
	printf("\n \n");
	return 0;
}
