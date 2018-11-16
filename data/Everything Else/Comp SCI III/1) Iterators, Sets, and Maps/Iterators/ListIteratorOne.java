//© A+ Computer Science
// www.apluscompsci.com

//ListIterator example


import java.util.ArrayList;
import static java.lang.System.*;
import java.util.ListIterator;

public class ListIteratorOne
{
   public static void main(String args[])
	{
		ArrayList<String> words;
		words = new ArrayList<String>();

		words.add("at");
		words.add("is");
		words.add("of");
		words.add("us");

		ListIterator<String> it = words.listIterator();
		System.out.println(it.next());
		System.out.println(it.next());
	}
}