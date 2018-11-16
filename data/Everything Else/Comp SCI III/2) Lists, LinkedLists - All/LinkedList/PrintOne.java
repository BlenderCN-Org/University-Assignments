//© A+ Computer Science
// www.apluscompsci.com

//printing a list example

import static java.lang.System.*;

public class PrintOne
{
   public static void main( String args[] )
   {
		ListNode x = new ListNode("10",
				     new ListNode("11",
				     new ListNode("12",null)));
		
		while(x!=null)
		{
		   out.println(x.getValue());
		}
   }
}