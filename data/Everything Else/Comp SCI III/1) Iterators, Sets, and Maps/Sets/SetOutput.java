//© A+ Computer Science
// www.apluscompsci.com

//printing out a set with basic iterator loop

import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class SetOutput
{
	public static void main(String args[])
	{
		Set<Double> doubleSet = new TreeSet<Double>();
		doubleSet.add(2.5);
		doubleSet.add(5.8);
		doubleSet.add(7.3);

		Iterator<Double> it = doubleSet.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}