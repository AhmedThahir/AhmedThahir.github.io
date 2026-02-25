#include <stdio.h>

int main()
{
	int choice;
	
	printf("Choice: "); scanf("%d", &choice);
	printf("\n");

	switch(choice)
	{
		case 1: {printf("Hello"); break;}
		case 2: {printf("Tea?"); break;}
		case 3: {printf("Coffee?"); break;}
		case 4: {printf("Milk Shake?"); break;}
		case 5: {printf("Juice?"); break;}
		default: printf("Bye");
	}

	printf("\n\n");

	return 0;
}	
