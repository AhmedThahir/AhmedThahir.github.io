class NumberTester
{
	
	static int j = 0;
	public static void main(String args[])
	{
		int[] a = new int[5];
		for(int i = 0; i<a.length; i++)
		{
			a[i] = (int) (Math.random() * 10.0f);
			System.out.println(a[i]);
		}

		System.out.println("\nOdd array");
		int[] new_array = split(a);
		for(int i = 0; i<j; i++)
			System.out.println( new_array[i] );
	}

	public static int[] split(int[] a)
	{
		int[] new_array = new int[a.length];

		for(int i = 0; i<a.length; i++)
		{	if(a[i] % 2 != 0)
			{	new_array[j] = a[i];
				j++;
			}
		}
		
		return new_array;
	}
}
