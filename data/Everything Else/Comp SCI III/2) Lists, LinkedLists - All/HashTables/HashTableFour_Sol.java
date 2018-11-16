//© A+ Computer Science
// www.apluscompsci.com

//hashtable example 4

import static java.lang.System.*;
import java.util.LinkedList;

class Table
{
	private LinkedList[] table;
	
	public Table(int size)
	{
		table = new LinkedList[10];
		for(int i = 0; i< table.length; i++)
		{
			table[i] = new LinkedList();
		}		
	}
	
	public void add(int value)
	{
		table[value%10].add(value);
	}
	
	public String toString()
	{
		String out = "";
		for( LinkedList list : table )
		{
			for( Object thing : list )
			{
				System.out.print(thing + " -> ");
			}  
			System.out.println();
		}
		return out;
	}	
}

public class HashTableFour_Sol
{
	public static void main ( String[] args )
	{
	   Table tab = new Table(10);
	   tab.add(3);
	   tab.add(7);
	   tab.add(11);
	   tab.add(31);
	   tab.add(17);
	   tab.add(15);	   
	   System.out.println(tab);	
	   
	   /*
	    *output
	    
			11 -> 31 ->
			
			3 ->
			
			15 ->
			
			7 -> 17 ->
		*/   	   
	}
}