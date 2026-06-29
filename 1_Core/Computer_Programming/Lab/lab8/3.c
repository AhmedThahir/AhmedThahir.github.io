#include <stdio.h>

int main()
{
	int n;
	printf("Number: "); scanf(" %d",&n);
	

	printf("\n");
	if(n==1)
		printf("%d is neither prime nor composite", n);
	else if(n==2)
		printf("%d is a prime no", n);
	else
	{
		int flag=1;
		for(int i = 2; i<=n/2; i++)
			if(n%i==0)
			{
				flag=0;
				break;
			}

		if(flag==1)
			printf("%d is prime", n);
		else
			printf("%d is composite", n);
	}
	

	printf("\n\n");
	return 0;
}
