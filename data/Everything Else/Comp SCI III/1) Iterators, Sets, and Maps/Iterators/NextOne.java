//© A+ Computer Science
// www.apluscompsci.com

//Iterator next example


import java.util.ArrayList;
import java.util.Iterator;

public class NextOne
{
   public static void main(String args[])
	{
		ArrayList<String> words;
		words = new ArrayList<String>();

		words.add("at");
		words.add("is");
		words.add("of");
		words.add("us");

		Iterator<String> it = words.iterator();
		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.next());
		//System.out.println(it.next()); //moves past the end of the list
													//throws a no-such element exception
	}
}
