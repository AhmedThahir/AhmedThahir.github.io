#include <stdio.h>

void find(int a[], int n);

int main()
{
	int n;
	printf("Number of elements\t"); scanf(" %d", &n);

	int a[n];
	for(int i =0; i<n; i++)
	{
		printf("Element %d\t", i+1);
		scanf(" %d", &a[i]);
		
	}

	find(a, n);

	return 0;
}

void find(int a[], int n)
{
	int s=a[0], l=a[0];
	for(int i = 0; i<n; i++)
	{
		if(a[i]<s)
			s = a[i];
		if(a[i]>l)
			l = a[i];

	}
	
	printf("\nSmallest is: %d \nLargest is: %d\n", s, l);


}
