//© A+ Computer Science
// www.apluscompsci.com

//printing out a set with the new for loop

import java.util.Set;
import java.util.TreeSet;

public class SetOutputNew_Sol
{
	public static void main(String args[])
	{
		Set<Double> doubleSet = new TreeSet<Double>();
		doubleSet.add(2.5);
		doubleSet.add(5.8);
		doubleSet.add(7.3);

		for(double dec : doubleSet)
		{
			System.out.println(dec);
		}
		
		System.out.println("\n\n");
		//same as
		
		for(Double dec : doubleSet)
		{
			System.out.println(dec);
		}		
	}
}