import java.util.Scanner;

class Prism
{
	private double l, w, h;

	Scanner inp = new Scanner(System.in);

	public void setPrism()
	{
		System.out.println("Enter in the following syntax: length width height \t eg: 3 10 5");

		l = inp.nextDouble();
		w = inp.nextDouble();
		h = inp.nextDouble();
	}

	public double topArea()
	{
		return l*w;
	}

	public double bottomArea()
	{
		return l*w;
	}

	public double leftArea()
	{
		return h*w;
	}
	
	public double rightArea()
	{
		return h*w;
	}

	public double frontArea()
	{
		return h*l;
	}

	public double backArea()
	{
		return h*l;
	}

	public double area()
	{
		return 2*(l*w + h*w + h*l);
	}
}

class TestPrism
{

	//Scanner inp = new Scanner(System.in);
	public static void main(String args[])
	{

		Prism p = new Prism();		p.setPrism();

		System.out.println("Top area " + p.topArea() );
		System.out.println("Bottom area " + p.bottomArea() );
		System.out.println("Left area " + p.leftArea() );
		System.out.println("Right area " + p.rightArea() );
		System.out.println("Front area " + p.frontArea() );
		System.out.println("Back area " + p.backArea() );
		System.out.println("Total area " + p.area() );
	}
}
