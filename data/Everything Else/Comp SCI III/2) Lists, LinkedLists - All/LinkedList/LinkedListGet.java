//© A+ Computer Science
// www.apluscompsci.com

//java linked list get example

import static java.lang.System.*;
import java.util.LinkedList;
import java.util.Collections;

public class LinkedListGet
{
	public static void main(String args[])
	{
		LinkedList<String> list = new LinkedList<String>();

		list.add("c");
		list.add("b");
		list.add("a");
		list.add(1,"d");  			//inserts at 1 and moves other items up

		out.println(list.get(0) );   	//c
		out.println(list.get(1) );   	//d

		out.println("first " + list.getFirst());
		out.println("last " + list.getLast());
	}
}