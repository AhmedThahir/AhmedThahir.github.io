class MyThread implements Runnable
{	
	private int start, 
		end, 
		sum;

	public MyThread(int s, int e)
	{
		start = s;
		end = e;
		sum = 0;
	}
	
	public void run()
	{
		for(int i = start; i <= end; i++)
			sum += i;
		System.out.println("Sum = " + sum);
	}
}

class ThreadTester
{
	public static void main(String args[])
	{
		Thread t1 = new Thread( new MyThread(1, 100) );
		Thread t2 = new Thread( new MyThread(101, 200) );
		Thread t3 = new Thread( new MyThread(201, 300) );
		
		t1.start(); t2.start(); t3.start();

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch(InterruptedException e)
		{
			System.out.println("Error");	
		}

	}
}
