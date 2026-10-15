#include <stdio.h>

int main()
{
	int n = 5; int a[10];
	int *p = a;

	for(int i = 0; i<5; i++)
		scanf("%d ", p+i );
	
	printf("\n\n");
	for(int i = 0; i<n; i++)
		printf("%x = %d\n", p+i, *(p+i) );

	return 0;
}
