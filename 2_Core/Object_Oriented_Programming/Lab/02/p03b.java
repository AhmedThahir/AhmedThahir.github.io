class p03b
{	
	public static void prime(int num)
	{
		int i = 2, flag = 1; // assuming prime
		while (i<num)
		{
			if (num % i == 0)
				flag = 0; // composite
			i++;
		}
		if(flag == 1)
			System.out.println(num);
	}

	public static void main(String args[])
	{
		int l = Integer.parseInt(args[0]), u = Integer.parseInt(args[1]);

		for (int i = l+1; i<u; i++)
		{
			prime(i);
		}
	}
}
