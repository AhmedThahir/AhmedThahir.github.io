class SearcherThr implements Runnable
{
	int[] a;
	int element, start, end;

	static int flag, index;

	public SearcherThr(int[] data, int el, int s, int e)
	{
		a = data;
		element = el;
		start = s;
		end = e;
	}
	
	public void run()
	{
		for(int i = start; i<=end; i++)
			if(a[i] == element)
			{
				flag = 1;
				break;
			}
	}

	public static void display()
	{
		if(flag == 1)
			System.out.println("Found at index: " + index);
		else
			System.out.println("Not found");
	}
}

class Tester04
{
	public static void main(String args[])
	{
		int[] data = {1, 2, 3, 4, 5};
		
		Thread t1 = new Thread( new SearcherThr(data, 2, 0, 2) );
		Thread t2 = new Thread( new SearcherThr(data, 2, 3, 4) );

		t1.start();
		t2.start();

		SearcherThr.display();
	}
}
