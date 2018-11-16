//© A+ Computer Science
// www.apluscompsci.com

//hash set int example

import java.util.Set;
import java.util.HashSet;

public class HashSetInt
{
	public static void main(String args[])
	{
		Set<Integer> intSet;
		intSet = new HashSet<Integer>();
		intSet.add(45);
		intSet.add(12);
		System.out.println(intSet.add(12));
		intSet.add(23);
		System.out.println(intSet);
	}
}