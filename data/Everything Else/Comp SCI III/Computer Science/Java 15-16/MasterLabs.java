

import java.util.*;
import java.io.*;

//If Iterator is Changed, Original is Changed
//If Original is Changed, Iterator is NOT Changed.

public class MasterLabs
{

    public static void main (String [] args)
    {
		Lab01();
    }

    public static void Lab01()
    {
    	int x = 0;

		Scanner kb = new Scanner (System.in);
    	ArrayList<String> list = new ArrayList<String>();

    	list.add("A");
    	list.add("B");
    	list.add("D");
    	list.add("E");
    	list.add("Y");
    	list.add("L");
    	list.add("N");
    	list.add("W");
    	list.add("U");
    	list.add("P");
    	list.add("I");
    	list.add("T");
    	list.add("R");
    	list.add("M");
    	list.add("V");

		Iterator it = list.iterator();

		System.out.println(list);
		System.out.println("Please Input a Letter to be Removed");
		String Answer = kb.next();

		while(x < 1)
		{
			if(it.next().equals(Answer))
			{
				it.remove();
				x++;
			}
		}

		System.out.println("///////////////////////////////////////////////////////////");
		System.out.println("The Letter you removed:: " + Answer);
		System.out.println("The new list is:: ");
		System.out.println(list);
    }






    }


