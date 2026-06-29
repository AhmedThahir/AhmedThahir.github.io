#include <stdio.h>

int
quad (int x, int y)
{
  if (x > 0 && y > 0)
    return 1;
  else if (x < 0 && y > 0)
    return 2;
  else if (x < 0 && y < 0)
    return 3;
  else
    return 4;
}

int
main ()
{
  float x, y;
  printf ("X-coordinate: ");
  scanf ("%f", &x);
  printf ("\nY-coordinate: ");
  scanf ("%f", &y);

  int q;

  if (x == 0 && y == 0)
    printf ("Origin");
  else if (x == 0)
    printf ("Y-axis");
  else if (y == 0)
    printf ("X-axis");
  else
    {
      q = quad (x, y);
      printf ("The quadrant is Q%d", q);
    }

  printf ("\n\n");
  return 0;
}
