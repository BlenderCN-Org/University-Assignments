//© A+ Computer Science
// www.apluscompsci.com

//basic map example 1

import java.util.*;
import java.util.TreeMap;

public class BasicMapOne
{
	public static void main(String args[])
	{
ArrayList<Integer> a = new ArrayList<Integer>();
a.add(5);
a.add(6);
a.add(9);
a.add(7);
a.add(2);
ListIterator<Integer> iterator = a.listIterator();
iterator.next();
iterator.set(1);
iterator.next();
iterator.set(4);
iterator.previous();
iterator.remove();
iterator.previous();
iterator.set(0);
System.out.println(a);
	}
}