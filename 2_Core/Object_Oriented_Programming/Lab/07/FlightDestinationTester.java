import java.util.ArrayList;
import java.util.Scanner;

class FlightInfo
{
	private String flightNo,
		source,
		destination;
	private float cost;

	FlightInfo(String fNo, String src, String dest, float c)
	{
		flightNo = fNo;
		source = src;
		destination = dest;
		cost = c;
	}

	public String getFlightNo()
	{
		return flightNo;
	}

	public String getSource()
	{
		return source;
	}
	
	public String getDestination()
	{
		return destination;
	}
	
	public float getCost()
	{
		return cost;
	}

}

class FlightListInfo
{
	ArrayList<FlightInfo> flist = new ArrayList<FlightInfo>();
	
	public FlightListInfo()
	{	

		addFlightInfo("A70", "DXB", "CHE", 1000.0f);
		//Scanner inp = new Scanner(System.in);
		
		addFlightInfo("A71", "DXB", "MUM", 1000.0f);
		addFlightInfo("A72", "DXB", "DEL", 1000.0f);
	
		for( FlightInfo i:flist )
			System.out.println(
				i.getFlightNo() + "\t" + i.getSource() + "\t" + i.getDestination() + "\t" + i.getCost()
			);

		for( FlightInfo i:getFlightsSrcDest("DXB", "CHE") )
		{
			System.out.print( i.getFlightNo() + "\t" + i.getCost() + "\n" );
		}
	}

	public void addFlightInfo(String fNo, String src, String dest, float cost)
	{
		FlightInfo a = new FlightInfo(fNo, src, dest, cost);
		flist.add(a);	
	}

	public ArrayList<FlightInfo> getFlightsSrcDest(String src, String dest)
	{
		ArrayList<FlightInfo> l = new ArrayList<FlightInfo>();

		for ( FlightInfo i:flist )
			if( i.getSource().equals(src) && i.getDestination().equals(dest) ) 
				l.add(i);

		return l;
	}
}

class FlightDestinationTester
{
	public static void main(String args[])
	{
		FlightListInfo a = new FlightListInfo();
	}
}
