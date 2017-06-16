
import java.io.*;
import java.util.*;
import java.util.Collections.*;
import java.lang.Math;
import java.lang.String;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class FindCustomers
{
	public static void main(String args[])
	{
		JSONParser parser = new JSONParser();
		List<Object> list = new ArrayList<Object>();
		try
		{
			JSONArray customers = (JSONArray) parser.parse(new FileReader("customers.json"));
			double x2=Math.toRadians(12.935076);
			double y2=Math.toRadians(77.614277);
			for(Object o:customers)
			{
				JSONObject customer = (JSONObject)o;
				String name =(String) customer.get("name");
				String uname =(String)customer.get("uname");
				Double lat = Double.parseDouble((String )customer.get("latitude"));
				Double lon = Double.parseDouble((String)customer.get("longitude"));
				double x1= Math.toRadians(lat);
				double y1= Math.toRadians(lon);
				double angle = Math.acos(Math.sin(x1) * Math.sin(x2)
                      + Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2));
				angle=Math.toDegrees(angle);
				double distance = 60*angle;
				if(distance<=100)
				{
					list.add(customer);
				}
				
			}
			Collections.sort(list,new Comparator<Object>()
				{
					@Override
					public int compare(Object c1, Object c2)
					{
						JSONObject customer1 = (JSONObject)c1;
						JSONObject customer2 = (JSONObject)c2;
						String s1 =(String)customer1.get("uname");
						String s2 =(String)customer2.get("uname");
						return s1.compareTo(s2);
					}
				});
			for(Object o:list)
			{
				JSONObject customer = (JSONObject)o;
				String name =(String) customer.get("name");
				String uname =(String)customer.get("uname");
				System.out.println(name+" "+uname);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}