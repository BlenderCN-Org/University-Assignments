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
		table = new LinkedList[size];
		
		//must do something here		
		
	}
	
	public void add(int value)
	{
		//add value to the table
		//use the table.length in your hash formula
	}
	
	public String toString()
	{
		String out = "";

		//add in a loop to get each LinkedList

			//add in a loop to add each value of each LinkedList to out


		return out;
	}	
}

public class HashTableFour
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