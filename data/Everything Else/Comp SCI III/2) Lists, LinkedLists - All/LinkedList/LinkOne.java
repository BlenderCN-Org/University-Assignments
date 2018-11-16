//© A+ Computer Science
// www.apluscompsci.com

//linking nodes together example one

import static java.lang.System.*;

public class LinkOne
{
   public static void main( String args[] )
   {
		ListNode x = new ListNode("10",
					new ListNode("11",
					new ListNode("12",null)));
		//add two more nodes			

		out.println(x.getValue());
		out.println(x.getNext().getNext().getValue());
		out.println(x.getNext().getValue());
		
		//add code to access the two new nodes
		
		
   }
}