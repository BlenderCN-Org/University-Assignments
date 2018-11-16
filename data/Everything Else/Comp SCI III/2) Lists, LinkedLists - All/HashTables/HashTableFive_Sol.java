//© A+ Computer Science
// www.apluscompsci.com

//hashtable example 5 - using ListNode

import static java.lang.System.*;

class Table
{
	private ListNode[] table;
	
	public Table(int size)
	{
		table = new ListNode[10];		
	}
	
	public void add(int value)
	{
	  table[value%10] = new ListNode( value, table[value%10] );
	}
	
	public String toString()
	{
		String out = "";
		for( ListNode list : table )
		{
			while( list != null )
			{
				System.out.print(list.getValue() + " -> ");
				list = list.getNext();
			}  
			System.out.println();
		}
		return out;
	}	
}

public class HashTableFive_Sol
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