#include <stdio.h>

int
prime (int n)
{
  int i = 2, flag = 1;
  while (i < n)
    {
      if (n % i == 0)
	{
	  flag = 0;
	  break;
	}
      i++;
    }


  if (flag == 1)
    return 1;
  else
    return 0;
}

int
main ()
{
  printf ("Number: ");
  int n;
  scanf (" %d", &n);

  if (n == 1)
    printf ("Neither prime nor composite");
  else if (prime (n))
    printf ("Prime");
  else
    printf ("Composite");


  printf ("\n\n");
  return 0;
}
