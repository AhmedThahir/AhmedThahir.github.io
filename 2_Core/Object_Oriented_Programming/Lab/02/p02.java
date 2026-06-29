class Squ
{
	float a = 3.0f, area;
	void area()
	{
		area = a*a;	
		System.out.println(area);
	}
}

class Tri
{
	float b = 3.0f, h = 3.0f, area;
	void area()
	{
		area = (1.0f/2.0f) * b * h;
		System.out.println(area);	
	}
}

class Cir
{
	float r = 3.0f, area;
	void area()
	{
		area = 3.14f * r * r;
		System.out.println(area);
	}
}

class Rec
{
	float l = 3.0f, b = 3.0f, area;
	void area()
	{
		area = l * b;
		System.out.println(area);
	}
}

class Cyl
{
	float r = 3.0f, h = 3.0f, area;
	void area()
	{
		area = (2 * 3.14f * r * h) + (2 * 3.14f * r * h);
		System.out.println(area);
	}		
}

class p02
{	

	public static void main(String args[])
	{
		//float f = 3f;
		//System.out.println(f);

		Squ s = new Squ();
		s.area();

		Tri t = new Tri();
		t.area();

		Cir c = new Cir();
		c.area();

		Rec r = new Rec();
		r.area();
		
		Cyl cyl = new Cyl();
		cyl.area();

	}
}










