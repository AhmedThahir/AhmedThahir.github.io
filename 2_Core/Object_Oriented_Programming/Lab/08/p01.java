class Person
{
	private String name,
		address;

	public Person(String name, String address)
	{
		this.name = name;
		this.address = address;
	}
	public String getName()
	{
		return name;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
	public String toString()
	{
		String text = "Person [name = " + name + ", address = " + address + "]";
		return text;
	}
}

class Student extends Person
{
	private String program;
	private int year;
	private double fee;

	public Student(String name, String address, String program, int year, double fee)
	{
		super(name, address);
		this.program = program;
		this.year = year;
		this.fee = fee;
	}

	public String getProgram()
	{
		return program;
	}
	public void setProgram(String program)
	{
		this.program = program;
	}
	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}
	
	public double getFee()
	{
		return fee;
	}

	public void setFee(double fee)
	{
		fee = this.fee;
	}

	public String toString()
	{
		String text = "Student[Person[name= " + getName() +
			", address=" + getAddress() +
			"], program =" +  getProgram() +
			", year =" + getYear() +
			", fee =" + getFee() +
			"]";
		return text;
	}

}

class Staff extends Person
{
	private String school;
	private double pay;

	public Staff(String name, String address, String school, double pay)
	{
		super(name, address);
		this.school = school;
		this.pay = pay;
	}

	public String getSchool()
	{
		return school;
	}

	public void setSchool(String school)
	{
		this.school = school;
	}

	public double getPay()
	{
		return pay;
	}

	public void setPay(double pay)
	{
		this.pay = pay;
	}
	
	public String toString()
	{
		String text = "Staff[Person[Name=" + getName() +
			", address=" + getAddress() +
			"], school=" + getSchool() +
			", pay=" + getPay() + "]";
		return text;
	}

}


class p01
{
	public static void main(String args[])
	{
		Person person = new Person("Ahmed", "Dubai");
		Student student = new Student("Ahmed", "Chennai", "CE", 2020, 35000d);
		Staff staff = new Staff("Thahir", "Dubai", "IHS", 50000d);
		
		System.out.println(person + "\n"); 
		System.out.println(student + "\n");
		System.out.println(staff);
	}
}
