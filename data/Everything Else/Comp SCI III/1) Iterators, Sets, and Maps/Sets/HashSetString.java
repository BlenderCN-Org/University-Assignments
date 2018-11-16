//© A+ Computer Science
// www.apluscompsci.com

//hash set string example

import java.util.Set;
import java.util.HashSet;

public class HashSetString
{
	public static void main(String args[])
	{
		Set<String> stringSet = new HashSet<String>();
		stringSet.add("AB");
		stringSet.add("23");
		stringSet.add("ab");
		System.out.println(stringSet);
	}
}