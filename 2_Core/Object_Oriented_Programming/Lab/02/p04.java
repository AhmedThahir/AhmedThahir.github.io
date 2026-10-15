import java.util.Scanner;
import java.util.*;

class p04
{
	public static int calc(int o1, char op, int o2)
	{
		switch(op)
		{
			case '+': return o1 + o2; // break;
			case '-': return o1 - o2; // break;
			case '*': return o1 * o2; //break;
			case '/': return o1 / o2; //break;
			case '%': return o1 % o2; //break;
			case '^': return (int) Math.pow(o1, o2); //break;
			default: System.out.println("invalid operation"); return 0;
		}
	}

	public static void main(String args[])
	{		
		Scanner inp = new Scanner(System.in);
		
		System.out.println("Enter in the following syntax: operand operation operand \t eg: 3 + 3");
		int o1 = inp.nextInt();
		char op = inp.next().charAt(0);
		int o2 = inp.nextInt();

		int output = calc(o1, op, o2);

		System.out.println("\nThe final value is " + output);
	}
}
