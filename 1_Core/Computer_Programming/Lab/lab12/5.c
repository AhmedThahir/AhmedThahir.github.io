#include <stdio.h>
#include <string.h>

int main()
{
	char ch[10], dh[10];
	//scanf(" %s", ch);
	//scanf(" %s", dh);
	gets(ch); gets(dh);


	if ( strcmp(ch, dh) == 0 )
		printf("Same");
	else 
	{
		printf("Not same");
	}
	
	return 0;
}
