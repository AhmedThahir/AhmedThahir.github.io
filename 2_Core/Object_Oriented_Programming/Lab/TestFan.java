
enum Level
{
	LOW (1), MEDIUMLOW, MEDIUM, MEDIUMHIGH, HIGH;
}



class Fan
{
	private String fanType, manufacturer, model;
	private boolean isOn;
	Level speed;
	
	public void setfan()
	{
		fanType = "huge";
		manufacturer = "Bajaj";
		model = "BM360";
		isOn = false;
		speed = Level.LOW;
	}
	public void getFan()
	{
		System.out.println("Fantype: " + fanType + 
		"\nManufacturer: " + manufacturer +
		"\nModel: " + model +
		"\nState: " + isOn +
		"\nSpeed: " + speed);
	}

	public void on()
	{
		isOn = true;
	}

	public void off()
	{
		isOn = false;
	}

	public void speedUp()
	{
		if(speed != Level.LOW)
			speed++;
	}
	public void speedDown()
	{
		if(speed != Level.HIGH)
			speed--;
	}

}

class TestFan
{
	public static void main(String args[])
	{
		Fan f = new Fan();
		System.out.println(f.speed);
	}
}
