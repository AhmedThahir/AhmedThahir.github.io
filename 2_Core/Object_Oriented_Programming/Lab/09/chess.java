import java.util.ArrayList;
import java.util.Scanner;

interface movable
{
	public void move(String newPos);
}

abstract class ChessPiece implements movable
{
	String name;
	String color;
	String curPos;
	
	public ChessPiece(String n, String c, String p)
	{
		name = n;
		color = c;
		curPos = p;

		System.out.println("Created " + color + " " + name + " at " + curPos);
	}

	public void move(String newPos)
	{	


		System.out.println(name + " moving from " + curPos + " to" + newPos);
		curPos = newPos;	
	}
}

class King extends ChessPiece
{
	King()
	{
		super("King", "White", "a5");
	}
}

class Queen extends ChessPiece
{
	Queen()
	{
		super("Queen", "White", "a6");
	}

}

class Pawn extends ChessPiece
{
	Pawn()
	{
		super("Pawn", "White", "a7");
	}

}

class chess
{

	static Scanner inp = new Scanner(System.in);
	static ArrayList<ChessPiece> al = new ArrayList<ChessPiece>();
	
	public static void menu()
	{	
		System.out.println("\nEnter 0 to exit or \nEnter Piece and new Position \t" +
			"1 - King, 2 - Queen, 3 - Pawn \n" +
			"eg: 2 d9\n");

		int s = inp.nextInt();			
		//String space = inp.next();
		String newPos = inp.nextLine();
		
		if(s == 0)
			System.exit(0);
		else
		{
			System.out.print("\033[H\033[2J");	
			
			al.get(s-1).move(newPos);
			menu();
		}
	}

	public static void main(String args[])
	{
		al.add( new King() );
		al.add( new Queen() );
		al.add( new Pawn() );

		menu();
	}
}






