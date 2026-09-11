#include <stdio.h>

int
main ()
{
  int a, b, c;
  printf ("Number 1: ");
  scanf ("%d", &a);
  printf ("Number 2: ");
  scanf ("%d", &b);


  char s;
  printf
    ("Enter + for addition \n - for subtraction \n * for multiplication\n / for division\n");
  scanf (" %c", &s);

  switch (s)
    {
    case '+':
      {
	c = a + b;
	printf ("The sum is %d", c);
	break;
      }
    case '-':
      {
	c = a - b;
	printf ("The difference is %d", c);
	break;
      }
    case '*':
      {
	c = a * b;
	printf ("The product is %d", c);
	break;
      }
    case '/':
      {
	c = a / b;
	printf ("The quotient is %d", c);
	break;
      }
    default:
      {
	printf ("%c is an invalid option", s);
      }
    }

  return 0;
}
