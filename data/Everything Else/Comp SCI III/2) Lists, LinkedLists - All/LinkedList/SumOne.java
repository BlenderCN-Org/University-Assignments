//© A+ Computer Science
// www.apluscompsci.com

//summing a list example 1

import static java.lang.System.*;

public class SumOne
{
   public static void main( String args[] )
   {
		ListNode x = new ListNode(11,
				     new ListNode(8,
				     new ListNode(5,null)));
		
		int sum=0;
		while(x!=null)
		{
		   sum=sum+(Integer)x.getValue();
		   x=x.getNext();
		}
		out.println(sum);
   }
}