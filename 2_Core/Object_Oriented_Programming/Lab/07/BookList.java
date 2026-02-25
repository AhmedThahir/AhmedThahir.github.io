import java.util.ArrayList;

class BookAuthorCopy
{
	private String bookName,
		authorName;
	private int numCopies;
	
	
	BookAuthorCopy(String b, String a, int n)
	{
		bookName = b;
		authorName = a;
		numCopies = n;
	}
	
	public String getBookName()
	{
		return bookName;	
	}

	public String getAuthorName()
	{
		return authorName;
	}

	public int getNumCopies()
	{
		return numCopies;
	}
}

class BookList
{
	static ArrayList<BookAuthorCopy> list = new ArrayList<BookAuthorCopy>();
	
	public static void main(String args[])
	{
		list.add( new BookAuthorCopy("blah", "Random", 2) );
		list.add( new BookAuthorCopy("ManU", "Thahir", 2) );
		list.add( new BookAuthorCopy("Liv", "Thahir", 3) );
		list.add( new BookAuthorCopy("Che", "Thahir", 5) );
		
		displayNumCopies("Thahir");
		displayNumCopies("Vetha");
	}
		
	public static void displayNumCopies(String author)
	{
		int count = 0;
		
		System.out.println("\nBooks by " + author);
		for (BookAuthorCopy i:list)
			if( i.getAuthorName().equals(author) )
			{	
				System.out.println(i.getBookName() + "\t" + i.getNumCopies());
				++count;
			}
		if(count == 0)
			System.out.println("No books by " + author);
	}
}
