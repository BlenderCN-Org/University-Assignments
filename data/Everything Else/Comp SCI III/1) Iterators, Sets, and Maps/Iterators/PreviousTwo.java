//© A+ Computer Science
// www.apluscompsci.com

//ListIterator previous example 2


import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class PreviousTwo
{
   public static void main(String args[])
	{
		ArrayList<String> words;
		words = new ArrayList<String>();
		words.add("at");
		words.add("up");
		words.add("or");
		
		ListIterator<String> it = words.listIterator();
		it.next();
		it.next();
		it.next();
		System.out.println(it.previous());
		System.out.println(it.previous());
		it.set("33");
		System.out.println(words);
	}
}