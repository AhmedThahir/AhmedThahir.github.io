class CustomException extends Exception
{

	//String errorText = "My message";
	CustomException()
	{
		super();
	}
}

class Prime
{
	Prime(int start, int end)
	{	
		
		try
		{
			if(start < 0 || end < 0)
				throw new CustomException();
				//throw new IllegalArgumentException("My message: The number is negative");
		System.out.println("The prime nos bw " + start + " and " + end + " are");
		
		for(int n = start+1; n < end; n++)
		{	
			int i = 2, flag = 1;

			while(i<n)
			{	if(n%i == 0)
				{
					flag = 0;
					break;
				}
				i++;
			}

			if(flag == 1)
				System.out.println(n);
		}

		} catch(CustomException e)
		{
		System.out.println("The number is negative");
			System.out.println("My message");
		}
	}
}

class PrimeTester
{
	public static void main(String args[])
	{
		new Prime(-1, -10);
	}
}
