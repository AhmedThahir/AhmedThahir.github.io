#include <stdio.h>

int n;

void
con (int dec, int bin[20])
{
  int i = 0;
  while (dec != 0)
    {
      bin[i] = dec % 2;
      i++;

      dec /= 2;
    }

  n = i;
}

int
main ()
{
  printf ("Decimal number: ");
  int dec;
  scanf (" %d", &dec);

  int bin[20];
  con (dec, bin);

  printf ("\n\n");
  for (int i = n - 1; i >= 0; i--)
    printf ("%d ", bin[i]);

  printf ("\n\n");
  return 0;
}
