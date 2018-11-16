//© A+ Computer Science
// www.apluscompsci.com

//printing out a map with basic iterator loop

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class HashMapOutput
{
	public static void main(String args[])
	{
		Map<Character,Integer> map;
		map = new HashMap<Character,Integer>();

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

		Iterator<Character> it;
		it = map.keySet().iterator();
		while(it.hasNext())
		{
			char c = it.next();
			System.out.println(c + " - " + map.get(c));
		}
	}
}