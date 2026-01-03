import java.util.Scanner;

class Tester3
{
	public static void main(String args[])
	{
		Scanner inp = new Scanner(System.in);

		String in = "";

		System.out.println("Enter your name");
		String name = inp.nextLine();
		
		in += Character.toUpperCase(name.charAt(0));

		for(int i = 1; i < name.length(); i++)
			if(name.charAt(i) == ' ')
				in += Character.toUpperCase(name.charAt(i+1));		
		System.out.println(in);

	}
}
