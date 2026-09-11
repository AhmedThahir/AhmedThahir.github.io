#include <stdio.h>
#include <string.h>
#include <ctype.h>

int main()
{
	char ch[10]; printf("String: "); scanf(" %s", ch);
	int vcount = 0, ccount = 0;

	int n = strlen(ch);
	for(int i = 0; i<n; i++)
	{
		if( isalpha(ch[i]) )
		{
		
			if(ch[i]=='a' || ch[i] == 'A' || ch[i] == 'e' || ch[i] == 'E' || ch[i] == 'i' || ch[i] == 'I' || ch[i] == 'o' || ch[i] == 'O' || ch[i] == 'u' || ch[i] == 'U')
				vcount++;
			else
				ccount++;
		}
	}


	printf("Vowels: %d \nConsonants: %d", vcount, ccount);

	return 0;
}
