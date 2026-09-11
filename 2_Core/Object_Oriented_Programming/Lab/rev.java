public class rev
{
	public static void main(String args[])
	{
		int n = Integer.parseInt(args[0]);
		
		int x = n, rev = 0, ld = 0;
		while (x!=0)
		{
			ld = x%10;
			rev = (rev*10) + ld;
			x/=10;
		}
		System.out.println("The reversed number = " + rev);	
	}
}
