class Test
{
	public static void main(String args[])
	{
		int a = 3, b = 0;
		try
		{
			System.out.println(a/b);
		} catch(Exception e)
		{
			System.out.println(
				"My message: blah \n"	
				+ e.getMessage()
			);
			e.printStackTrace();
		
		}
	}
}
