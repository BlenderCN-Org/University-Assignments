//© A+ Computer Science
// www.apluscompsci.com

//using split to load values into a set

import static java.lang.System.*;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;

public class SetSplit
{
	public static void main(String args[])
	{
		String[] words = "abc cde fgh ijk".split(" ");   
		Set<String> set = new TreeSet<String>(Arrays.asList(words));
		System.out.println(set);
	}
}