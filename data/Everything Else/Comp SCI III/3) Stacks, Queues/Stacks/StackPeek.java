//© A+ Computer Science
// www.apluscompsci.com

//stack peek example

import static java.lang.System.*;
import java.util.Stack;

public class StackPeek
{
	public static void main( String args[] )
	{
		Stack<Integer> s;
		s = new Stack<Integer>();
		s.push(88);
		s.push(23);
		s.push(11);
		out.println("stack before peek :: "+s);
		out.println(s.peek());
		out.println("stack after peek :: "+s);
	}
}
