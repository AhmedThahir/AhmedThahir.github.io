import java.util.Scanner;

class Employee
{
	Scanner inp = new Scanner(System.in);
	
	String employee_id;
	float[] salary = new float[3];
	float total;
	float bonusTotal;

	Employee()
	{
		System.out.println("Enter Name ");
		employee_id = inp.nextLine();
		
		System.out.println("\nEnter basic, HRA, and TA of employee \t Eg: 3000 500 300");
		for(int i = 0; i < salary.length; i++)
			salary[i] = inp.nextFloat();
		
		calcTotal();
		calcBonus();		
	}

	void calcTotal()
	{
		total = 0.0f;
		for(int i = 0; i < salary.length; i++)
			total += salary[i];
	}

	void calcBonus()
	{
		bonusTotal = 0.10f * total;
		total += bonusTotal;
	}



}

class EmployeeTester
{
	
	public static void main(String args[])
	{
		Scanner inp = new Scanner(System.in);
		System.out.println("Enter the number of employees");
		int n = inp.nextInt();

		Employee[] a = new Employee[n];
		for(int i = 0; i<a.length; i++)
			a[i] = new Employee();
		disp(a);
	}

	public static void disp(Employee[] a)
	{	
	//	System.out.println("Object created");
		Employee max = a[0];
		for(int i = 1; i < a.length; i++)
			if(a[i].total > max.total)
				max = a[i];
		System.out.println("\nHighest Earner after Bonus:");
		System.out.println(max.employee_id + "\n" + max.total);
	}		

}








