#include <stdio.h>

int sum(int a[], int n); 

int main()
{	
	int n; 
	printf("Number of elements: ");	scanf("%d", &n);
	
	int a[n];
	for(int i = 0; i<n; i++)
	{	
		printf("ELement %d\t", i+1);
		scanf("%d", &a[i]);
	}

	int s = sum(a,n);

	printf("\n\nThe sum is: %d", s);

	return 0;	
}

int sum(int a[], int n)
{
	int sum = 0;
	for(int i = 0; i<n; i++)
		sum += a[i];
	return sum;
}
