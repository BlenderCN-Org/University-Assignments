//© A+ Computer Science
// www.apluscompsci.com

//hashtable example 2

import static java.lang.System.*;
import java.util.LinkedList;

public class HashTableTwo
{
	public static void main ( String[] args )
	{
		LinkedList[] hashTable = new LinkedList[5];
		
		for( LinkedList list : hashTable )
		{
			System.out.println(list);
		}   			
			
		for(int i = 0; i< hashTable.length; i++)
		{
			hashTable[i] = new LinkedList();
		}
		
		for( LinkedList list : hashTable )
		{
			System.out.println(list);
		}   	   
	}
}