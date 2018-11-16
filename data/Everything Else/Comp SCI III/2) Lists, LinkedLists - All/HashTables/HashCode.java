//© A+ Computer Science
// www.apluscompsci.com

//hashcode example

import static java.lang.System.*;

public class HashCode
{
  public static void main ( String[] args )
  {
		Character c = new Character('a');
		System.out.println(c.hashCode());
		
		c = new Character('0');
		System.out.println(c.hashCode());
		
		c = new Character('A');
		System.out.println(c.hashCode());     
		
		Integer num = 45;		//autoboxing
		System.out.println(num.hashCode());
		
		num = new Integer(101);		//boxing up the int the long way
		System.out.println(num.hashCode());  
		
		String s = "a";		
		System.out.println(s.hashCode());  
     	
     s = "ab";		
     System.out.println(s.hashCode());      //how exactly did this number come up? 	     	   
  }
}