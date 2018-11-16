//© A+ Computer Science
// www.apluscompsci.com

//java linked list example

import static java.lang.System.*;
import java.util.LinkedList;
import java.util.Collections;

public class LinkedListDemo
{
	public static void main(String args[])
	{
		LinkedList<String> list = new LinkedList<String>();

		list.add("c");
		list.add("b");
		list.add("a");
		list.add(1,"d");  			//inserts at 1 and moves other items up

		out.println(list);

		out.println(list.get(0) );   	//c
		out.println(list.get(1) );   	//d

		out.println(list.indexOf("b") + "\n\n");   //outs 2

		Collections.sort(list);						//puts list in order of type

		for( int i = 0; i < list.size(); i++ )
		   out.println(list.get(i));
		out.println();

		Collections.reverse(list);					//reverses the order

		for(String s : list)
		{
			out.print(s + "  ");
		}
		out.println("\n\n");

		out.println("first item :: \t" + list.getFirst());
		out.println("last item :: \t" + list.getLast());
	}
}