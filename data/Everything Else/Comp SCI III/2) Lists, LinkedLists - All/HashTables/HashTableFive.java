//© A+ Computer Science
// www.apluscompsci.com

//hashtable example 5 - using ListNode

import static java.lang.System.*;

class Table
{
	private ListNode[] table;
	
	public Table(int size)
	{
		//size the array	
	}
	
	public void add(int value)
	{
		//add value to the hashtable
		//use table.length as part of the hash formula
	}
	
	public String toString()
	{
		String out = "";
		
		//loop through the table and access each bucket
			//loop through each bucket
				//add each value to out
		
		return out;
	}	
}

public class HashTableFive
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
	    
	      31 -> 11 ->
			
			3 ->
			
			15 ->
			
			17 -> 7 ->
		*/					   		   	   
	}
}