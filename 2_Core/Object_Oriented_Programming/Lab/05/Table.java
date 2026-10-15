import java.util.Scanner;

class Table
{	

	static Scanner inp = new Scanner(System.in);
	public static void main(String args[])
	{
		System.out.println("Enter the no of rows");
		int m = inp.nextInt();
		
		printTable(m);
	}
	public static void printTable(int m)
	{	int n = 10; // no of columns
		for(int i = 1; i <= m; i++)
		{	for(int j = i; j <= i*n; j+=i)
				System.out.print(j + "\t");
			System.out.println("\n");
		}
	}
}
