//© A+ Computer Science
// www.apluscompsci.com

//basic map example 3

import java.util.Map;
import java.util.HashMap;

public class BasicMapThree
{
	public static void main(String args[])
	{
		Map<Integer,Double> map;
		map = new HashMap<Integer,Double>();
		map.put(1,3.5);
		map.put(2,7.7);
		map.put(1,8.9);
		map.put(4,3.2);
		map.put(5,5.5);
		System.out.println(map.put(1,9.5));		
		System.out.println(map.put(2,6.6));

		System.out.println(map.get(1));
		System.out.println(map.get(2));
		System.out.println(map.get(7));
	}
}