class MyThread implements Runnable
{	
	private int start, 
		end, 
		sum, 
		threadNum;

	public MyThread(int n, int s, int e)
	{
		threadNum = n+1;
		start = s;
		end = e;
		sum = 0;
	}
	
	public void run()
	{
		for(int i = start; i <= end; i++)
			sum += i;
		System.out.println("Thread " + threadNum + " Sum = " + sum);
	}
}

class Tester03
{
	public static void main(String args[])
	{
		int num = 10,
			n = 4;
		
		int start = 1, end = num/n;
		int increment = end - start + 1;

		Thread[] t = new Thread[n]; // array containing n threads
		for(int i = 0; i<n; i++)
		{
			t[i] = new Thread( new MyThread(i, start,end) );
			t[i].start();
			
			start += increment;
			end += increment;
		}

		try {
			for(int i = 0; i<n; i++)
				t[i].join();
		} catch(InterruptedException e)
		{
			System.out.println("Error");	
		}

	}
}
