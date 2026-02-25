#include <stdio.h>
#include <string.h>

int main()
{
	char first[10], middle[10], last[10], full[30];

	scanf(" %s", first);
	scanf(" %s", middle);
	scanf(" %s", last);

	strcat(full, first);
	strcat(full, " ");
	strcat(full, middle);
	strcat(full, " ");
	strcat(full, last);

	printf("%s", full);

	return 0;
}
