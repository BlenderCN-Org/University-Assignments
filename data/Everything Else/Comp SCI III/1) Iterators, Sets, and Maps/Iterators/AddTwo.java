//© A+ Computer Science
// www.apluscompsci.com

//list iterator add example 2


import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class AddTwo
{
   public static void main(String args[])
	{
		ArrayList<String> words;
		words = new ArrayList<String>();

		words.add("is");
		words.add("us");

		ListIterator<String> it = words.listIterator();
		it.add("##");

		//it.remove();		//can't call remove until next or previous have been called

		System.out.println(it.previous());

		System.out.println(words);

		System.out.println(it.next());
		it.set("33");

		System.out.println(words);
		System.out.println(it.next());
		it.set("44");

		System.out.println(words);
	}
}