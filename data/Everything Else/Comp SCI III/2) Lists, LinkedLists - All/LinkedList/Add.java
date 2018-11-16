//© A+ Computer Science
// www.apluscompsci.com

//linking nodes / adding to the front example

import static java.lang.System.*;

public class Add
{
   public static void main( String args[] )
   {
   	//this example demonstrates how to add
   	//nodes to the front of a linked list
   	
		ListNode front=null;
		front = new ListNode("10", front);
		front = new ListNode("11",front);
		front = new ListNode("12",front);

		out.println(front.getValue());
		out.println(front.getNext().getNext().getValue());
		out.println(front.getNext().getValue());
   }
}