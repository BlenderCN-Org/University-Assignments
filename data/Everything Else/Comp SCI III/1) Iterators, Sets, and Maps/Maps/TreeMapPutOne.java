//© A+ Computer Science
// www.apluscompsci.com

//map put example 1

import java.util.Map;
import java.util.TreeMap;

public class TreeMapPutOne
{
	public static void main(String args[])
	{
		Map<Character,Integer> map;
		map = new TreeMap<Character,Integer>();

		String s = "dogcatchickenpigtreecartruckbeeflea";
		

		
		//add code to put each letter and a count into map
			//a - 1 etc.



		System.out.println(map.get('a'));
		System.out.println(map.get('x'));
		System.out.println(map.get('c'));
		System.out.println(map.get('t'));
		System.out.println(map.get('i'));
		System.out.println(map.get('q'));		
		System.out.println(map.get('e'));
		
		//output
		/*
		3
		null
		5
		3
		2
		null
		6
		*/
	}
}