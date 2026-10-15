public class interest
{
	public static void main(String args[])
	{
		float p = Float.parseFloat(args[0]),
		r = Float.parseFloat(args[1]),
		t = Float.parseFloat(args[2]);

		float i = (p * r * t) / 100;
		System.out.println("the interest is " + i);

	}
}
