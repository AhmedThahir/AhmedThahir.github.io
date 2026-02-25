import java.util.ArrayList;

class p01
{
	public static void main(String args[])
	{
		String[] nameArray = {
			"Raunak", 
			"Kelvin", 
			"Sameer",
			"Melvin",
			"Sheena"
		};

		ArrayList<String> nameList = new ArrayList<String>();
		for (String s:nameArray)
			nameList.add(s);
		
		System.out.println("Initial ArrayList");
		for (String s:nameList)
			System.out.println(s);

		nameList.set(0, "Darshan");
		nameList.remove(1);
		System.out.println(
			"\n" +
			nameList.get(0) + " " + nameList.get(2)
		);

		System.out.println("Length: " + nameList.size() );

		System.out.println("\nFinal ArrayList");
		for (String s:nameList)
			System.out.println(s);
	}
}








