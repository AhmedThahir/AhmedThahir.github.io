class Shape
{
	private String color;
	private boolean filled;

	public Shape()
	{
		color = "red";
		filled = true;
	}

	public Shape(String color, boolean filled)
	{
		this.color = color;
	}
	
	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public boolean isFilled()
	{
		return filled;
	}

	public void setFilled(boolean filled)
	{
		this.filled = filled;
	}

	public String toString()
	{
		text = "A Shape with color of " + color + " and";
		if(isFilled() == 0)
			text += "not";
		text += "filled";
	}
}

class Circle
{
	private double radius;	

	public Circle()
	{
		this(1);
	}

	public Circle(double radius)
	{
		setRadius(radius);
	}

	public Circle(double radius, String color, boolean filled)
	{
		this(radius);
		this.color = color;
		this.filled = filled;
	}

	public double getRadius()
	{
		return radius;
	}
		
	public void setRadius(double radius)
	{
		this.radius = radius;
	}

	public double getArea()
	{
		return 3.14d * Math.pow(radius, 2);
	}

	public double getPerimeter()
	{
		return 2 * 3.14d * radius;
	}

	public String toString()
	{
		text = "A circle with radius =" + radius +", which is a subclass of " + super.toString();
		return text;
	}
}

class Rectangle
{
	private double width,
		length;

	public Rectangle()
	{
		this(1, 1);
	}

	public Rectangle(double width, double length)
	{
		this.width = width;
		this.length = length;
	}
	
	public Rectangle(double width, double length, String color, boolean filled)
	{
		this(width, length);
		this.color = color;
		this.filled = filled;
	}

	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public double getLength()
	{
		return length;
	}

	public void setLength(double length)
	{
		this.length = length;
	}

	public double getArea()
	{
		return length * width;
	}
	public double getPerimeter()
	{
		return 2*(length + width);
	}
}
