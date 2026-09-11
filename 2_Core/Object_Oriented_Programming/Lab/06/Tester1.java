import java.util.Scanner;

class Tester1
{
	public static void main(String args[])
	{
		Scanner inp = new Scanner(System.in);

		String ID = "";
		String email = "f";

		// 1
		System.out.println("Year: "); int year = inp.nextInt(); 
		ID += Integer.toString(year);
		email += Integer.toString(year);
		
		// 2
		ID += "A";
		System.out.println("Discipline: (Chem - 1, EEE - 3, Mech - 4, CS - 7) "); int d = inp.nextInt();
		ID += Integer.toString(d);
		
		// 3
		ID += "PS";

		// 5
		int rand = (int) (Math.random() * 1000f); // 0 - 999		
		if (rand <= 9)
			ID += "00"; // 009
		else if (rand <= 99)
			ID += "0"; // 029
		else
			; // 120
		
		ID += Integer.toString(rand);
		email += Integer.toString(rand) + "@";
		
		// 4
		System.out.println("Campus: (Pilani - p, Hyderabad - h, Goa - g, Dubai - d) ");	
		char s = inp.next().charAt(0);	
		
		switch(s)
		{
			case 'p': ID += "P"; email += "pilani"; break;
			case 'h': ID += "H"; ; email += "hyderabad" ; break;
			case 'g': ID += "G"; ; email += "goa"; break;
			case 'd': ID += "U"; ; email += "dubai"; break;
			default: System.out.println("Incorrect option");
		}
		email += ".bits-pilani.ac.in";

		System.out.println("\n" + ID + "\t" + email);
		
	}
}






