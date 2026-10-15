#include <stdio.h>
#include <string.h>

int isPalindrome(char ch[10])
{
	int n = strlen(ch);

	int i = 0, j = n-1;
	int flag = 1;
	while(i<n && j>=0)
	{
		if(ch[i] != ch[j])
		{
			flag = 0;
			break;
		}

		i++;
		j--;
	}

	if(flag == 1)
		return 1;		
	else
		return 0;

}

int main()
{
	char ch[10]; printf("String: "); scanf(" %s", ch);

	printf("\n %d", isPalindrome(ch) );

	return 0;
}
