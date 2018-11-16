//© A+ Computer Science
// www.apluscompsci.com

//making a single node example

import static java.lang.System.*;

public class OneNode
{
   public static void main( String args[] )
   {
		Linkable node = new ListNode("10", null);

		out.println(node.getValue());
		out.println(node.getNext());
   }
}