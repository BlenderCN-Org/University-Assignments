//© A+ Computer Science
// www.apluscompsci.com

//linking nodes together example two

import static java.lang.System.*;

public class LinkTwo
{
   public static void main( String args[] )
   {
		ListNode x = new ListNode(10.3, null);
		ListNode y = new ListNode(11.0, x);
		ListNode z = new ListNode(2.5, y);
		//add two more nodes to the list

		out.println(z.getValue());
		out.println(z.getNext().getNext().getValue());
		out.println(z.getNext().getValue());
		
		//add in a loop to print out all nodes in the list
		
		
   }
}