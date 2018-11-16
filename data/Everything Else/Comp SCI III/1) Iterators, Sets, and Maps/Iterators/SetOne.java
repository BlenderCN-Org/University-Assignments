//© A+ Computer Science
// www.apluscompsci.com

//list iterator set example 1


import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class SetOne
{
   public static void main(String args[])
	{
		ArrayList<String> words;
		words = new ArrayList<String>();

		words.add("at");
		words.add("is");
		words.add("us");

		ListIterator<String> it = words.listIterator();

		System.out.println(it.next());
		it.set("###");
		System.out.println(it.next());
		System.out.println(words);
		System.out.println(it.previous());
	}
}
