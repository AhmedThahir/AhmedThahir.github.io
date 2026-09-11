abstract class Book implements Comparable<Book>
{
	private String name;
	private double cost;

	Book(String n, double c)
	{
		name = n;
		cost = c;
	}

	public int compareTo(String n2)
	{
		return name.compareTo(n2);
	}
}

class GeneralizedSearch
{
	public static boolean search(Object[] arr, Object item)
	{	
		boolean flag = false;
		for(int i = 0; i<arr.length; i++)
			if(arr[i] == item)
			{
				flag = true;
				break;
			}

		return flag;
	}
}

class BookTester
{
	public static void main(String args[])
	{
		Book[] a = new Book[3];
		for(int i = 0; i<a.length; i++)
			
	}
}
