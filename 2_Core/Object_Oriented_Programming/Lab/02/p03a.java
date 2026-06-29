import java.util.Scanner;

class p03a
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
		Scanner inp = new Scanner(System.in);
		
		System.out.println( "Enter the range (lower upper)" );
		int l = inp.nextInt(), u = inp.nextInt();
		
		System.out.println("\n \nThe prime nos are");
		for (int i = l+1; i<u; i++)
		{
			prime(i);
		}
	}
}
