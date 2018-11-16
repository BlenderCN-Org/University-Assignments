//© A+ Computer Science
// www.apluscompsci.com

//hashtable example 1

import static java.lang.System.*;

public class HashTableOne
{
	public static void main ( String[] args )
	{
		Object[] hashTable = new Object[10];
		
		Character c = new Character('1');
		hashTable[c.hashCode()%10] = c;
		
		Integer num = new Integer(113);		//boxing up the int the long way
		hashTable[num.hashCode()%10] = num;
		
		String s = "e";		
		hashTable[s.hashCode()%10] = s;
		
		//add more objects to the hashtable
		
		
		
		
		
			
		for( Object thing : hashTable )
		{
			System.out.println(thing);
		}   	   
	}
}