import java.util.Scanner;

class Employee
{
	Scanner inp = new Scanner(System.in);
	private String id,
		name;
	private int dept;
	private float basic,
		allowance,
		totalSalary;

	Employee()
	{
		id = "2020A7PS0198U";
		name = "Ahmed Thahir";

		System.out.println("Enter the department <space> basic salary \t eg: 2 300.35");
		dept = inp.nextInt();
		basic = inp.nextFloat();
		
		calculateSalary();
		displayDetails();
	}
	public void calculateSalary()
	{
		float hra = 0.40f * basic;
		switch(dept)
		{
			case 1: allowance = 1500; break;
			case 2: allowance = 2500; break;
			case 3: allowance = 3500; break;
			default: System.out.println("Error");
		}
		totalSalary = basic + hra + allowance;
	}
	public void displayDetails()
	{
		System.out.println("ID: " + id);
		System.out.println("Name: " + name);
		System.out.println("Department: " + dept);
		System.out.println("Total Salary: " + totalSalary);
	}
}

class EmployeeTester
{
	public static void main(String args[])
	{
		Employee e1 = new Employee();
	}
}
