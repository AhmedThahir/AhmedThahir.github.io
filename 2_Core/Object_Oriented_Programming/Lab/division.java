public class division
{
	public static void main(String args[])
	{
		float dividend = Integer.parseInt(args[0]), divisor = Integer.parseInt(args[1]);
		
		float q = dividend / divisor,
		r = dividend % divisor;

		System.out.println("The quotient is " + q);
		System.out.println("The remainder is " + r);
	}

}

