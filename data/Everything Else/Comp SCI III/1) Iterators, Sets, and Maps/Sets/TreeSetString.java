//© A+ Computer Science
// www.apluscompsci.com

//treeset string example

import java.util.Set;
import java.util.TreeSet;

public class TreeSetString
{
	public static void main(String args[])
	{
		Set<String> stringSet = new TreeSet<String>();
		stringSet.add("AB");
		stringSet.add("23");
		stringSet.add("ab");
		System.out.println(stringSet);
	}
}