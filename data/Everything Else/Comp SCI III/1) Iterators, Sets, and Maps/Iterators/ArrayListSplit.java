//© A+ Computer Science
// www.apluscompsci.com

//using split to load values into an arraylist

import static java.lang.System.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListSplit
{
	public static void main(String args[])
	{
		String[] words = "abc cde fgh ijk".split(" ");   
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(words));
		System.out.println(list);
	}
}