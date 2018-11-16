//© A+ Computer Science
// www.apluscompsci.com

//printing out a map with the new for loop

import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;

public class TreeMapOutputNew
{
	public static void main(String args[])
	{
		Map<Character,Integer> map;
		map = new TreeMap<Character,Integer>();

		String s = "cabcdefghihabcdc";
		for(char c : s.toCharArray())
		{
			if(map.get(c)==null)
			{
				map.put(c,1);
			}
			else
			{
			   map.put(c,map.get(c)+1);
			}
		}

		for(char c : map.keySet())
		{
			System.out.println(c+" - "+map.get(c));
		}
	}
}