#include <stdio.h>

int
main ()
{
  char ch;
  printf ("Enter the character: ");
  scanf ("%c", &ch);

  switch (ch)
    {
    case 'a':
    case 'A':
    case 'e':
    case 'E':
    case 'i':
    case 'I':
    case 'o':
    case 'O':
    case 'u':
    case 'U':
      printf ("%c is a vowel", ch);
      break;
    default:
      {
	if (ch >= 65 && ch <= 90 || ch >= 97 && ch <= 122)
	  printf ("%c is a consonant", ch);
	else
	  printf ("%c is an invalid alphabet", ch);
      }
    }



  return 0;
}
