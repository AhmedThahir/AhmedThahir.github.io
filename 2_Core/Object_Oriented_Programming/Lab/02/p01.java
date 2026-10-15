class p01
{
	public static void main(String args[])
	{
		int min = Integer.parseInt(args[0]);
		int n = args.length, num;
			
		for(int i = 0; i < n; i++)
		{	
			num = Integer.parseInt(args[i]);
			min = (num<min)?num:min;
		}
		System.out.println("the smallest number is " + min);
	}
}
