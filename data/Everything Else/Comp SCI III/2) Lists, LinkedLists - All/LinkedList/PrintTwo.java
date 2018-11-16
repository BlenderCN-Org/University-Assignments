//© A+ Computer Science
// www.apluscompsci.com

//printing a list example two

import static java.lang.System.*;

public class PrintTwo
{
   public static void main( String args[] )
   {
		ListNode x = new ListNode("10",
				     new ListNode("11",
				     new ListNode("12",null)));
		
		while(x!=null)
		{
		   out.println(x.getValue());
		   x=x.getNext();
		}
				
		System.out.println(x);		//why is this null??
											//do we want x to be null??
   }
}