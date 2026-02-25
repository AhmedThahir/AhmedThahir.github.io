import java.util.Scanner;

class CreditCard
{
	Scanner inp = new Scanner(System.in);
	private String name, cardNo, expiryMonth;
	private boolean enabled;
	private int pin,
		cardType, // platinum = 3, gold = 2, silver = 1
		currentCredit;
	int creditLimit = 5000;
	
	public CreditCard()
	{
		name = "Ahmed Thahir"; 
		cardNo = "34a48n2";
		expiryMonth = "June";
		enabled = false;
		pin = 3245;
		cardType = 3; // platinum = 3, gold = 2, silver = 1
		currentCredit = 3500;
	}
	public void changePin(int newPin)
	{
		pin = newPin;
	}
	public void transact(int amt)
	{	
		System.out.println("Enter the pin"); 
		int iPin = inp.nextInt(); //inputted pin
		
		int newCredit = currentCredit - amt;
		
		if(enabled == false)
		{
			System.out.println("The card is deactivated");
		}
		else if (pin != iPin)
		{
			System.out.println("Incorrect pin");
		}
		else if (newCredit > creditLimit)
		{
			System.out.println("New credit exceeds limit");
		}
		else
		{	
			currentCredit-= amt;
		}
	}
	public void changeCardStatus(boolean status)
	{
		enabled = status;
	}
	public void display()
	{	String disType = "esnetns";
		
		switch(cardType)
		{
			case 1: disType = "Silver"; break;
			case 2: disType = "Gold"; break;
			case 3: disType = "Platinum"; break;
			default: System.out.println("Error");
		}
		

		// System.out.println(disType);

		System.out.println(
			"Name: " + name + "\n" +
			"Card Number:" + cardNo + "\n" +
			"Status: " + enabled + "\n" +
			"Pin: " + pin + "\n" +
			"Expiry Month" + expiryMonth + "\n" +
			"Type: " + disType + "\n" +
			"Current Credit: " + currentCredit + "\n" + 
			"Credit Limit: " + creditLimit
		);
	}
}


class CreditTester
{
	public static void main(String args[])
	{
		Scanner inp = new Scanner(System.in);
		CreditCard c1 = new CreditCard();	
		
		System.out.println("Enter the amount to transact");
		int amt = inp.nextInt();
		c1.transact(amt);

		System.out.println("Enter the new pin");
		int newPin = inp.nextInt();
		c1.changePin(newPin);
		
		System.out.println("Enter the status of the card (0 for disabled, 1 for enabled)");
		boolean status;
		if (inp.nextInt() == 1)
			status = true;
		else
			status = false;
		c1.changeCardStatus(status);

		c1.display();
	}
}










