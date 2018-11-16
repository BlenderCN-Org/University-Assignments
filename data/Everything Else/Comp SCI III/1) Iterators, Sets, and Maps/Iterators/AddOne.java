//© A+ Computer Science
// www.apluscompsci.com

//list iterator add example 1


import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class AddOne
{
   public static void main(String args[])
	{
		ArrayList<String> words;
		words = new ArrayList<String>();

		words.add("is");
		words.add("us");

		ListIterator<String> it = words.listIterator();
		it.add("##");
		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.previous());
		it.set("##");
		System.out.println(words);
	}
}