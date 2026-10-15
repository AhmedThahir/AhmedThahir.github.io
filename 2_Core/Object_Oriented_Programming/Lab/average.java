class average
{
	public static void main(String args[])
	{
		// System.out.println("hello");
		float num = 0, sum = 0, avg = 0;
		for (int i = 0; i<3; i++)
		{
			num = Integer.parseInt(args[i]);

			sum += num;
		}
		avg = sum/3;
		System.out.println("Sum is " + sum);
		System.out.println("Avg is " + avg);

	}
}
