class Tester2
{
	public static void main(String args[])
	{
		String hi = "\"Hi, ";		// "Hi,
		String mom = "mom.\":";	// mom.":
		
		String a = hi + "\n" + mom;
		
		String b = "";
		b = b.concat(hi);
		b = b.concat("\n");
		b = b.concat(mom);

		System.out.println(a + "\n\n" + b);
	}
}
