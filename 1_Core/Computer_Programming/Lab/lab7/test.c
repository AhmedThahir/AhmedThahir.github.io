#include <stdio.h>

int
main ()
{
  int n;
  printf ("Enter the value for n: ");
  scanf ("%d", &n);

  int a = 0, b = 1, c = a + b;
  printf ("%d %d ", a, b);


  while (c <= n)
    {

      printf ("%d ", c);
      c = a + b;

      a = b;
      b = c;
    }

  return 0;
}
