//© A+ Computer Science
// www.apluscompsci.com

//Iterator hasNext example


import java.util.ArrayList;
import java.util.Iterator;

public class HasNext
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
		while(it.hasNext())
		{
		   System.out.println(it.next());
		}
	}
}
