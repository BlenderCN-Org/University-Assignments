//© A+ Computer Science
// www.apluscompsci.com

//map put example 1

import java.util.Map;
import java.util.TreeMap;

public class TreeMapPutOne_Sol
{
	public static void main(String args[])
	{
		Map<Character,Integer> map;
		map = new TreeMap<Character,Integer>();

		String s = "dogcatchickenpigtreecartruckbeeflea";
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

		System.out.println(map.get('a'));
		System.out.println(map.get('x'));
		System.out.println(map.get('c'));
		System.out.println(map.get('t'));
		System.out.println(map.get('i'));
		System.out.println(map.get('q'));		
		System.out.println(map.get('e'));
	}
}