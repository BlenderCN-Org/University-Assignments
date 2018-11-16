//© A+ Computer Science
// www.apluscompsci.com

//tree set remove example

import java.util.Set;
import java.util.TreeSet;

public class TreeSetRemove
{
	public static void main(String args[])
	{
		Set<Double> doubleSet = new TreeSet<Double>();
		doubleSet.add(2.5);
		doubleSet.add(5.8);
		doubleSet.add(7.3);
		System.out.println(doubleSet);
		doubleSet.remove(5.8);
		doubleSet.remove(0.0);
		System.out.println(doubleSet);
	}
}