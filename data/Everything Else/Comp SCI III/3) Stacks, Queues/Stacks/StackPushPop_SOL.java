//© A+ Computer Science
// www.apluscompsci.com

//stack push and pop example

import static java.lang.System.*;
import java.util.Stack;

public class StackPushPop_SOL
{
	public static void main( String args[] )
	{
		Stack<Integer> s;
		s = new Stack<Integer>();
		s.push(88);
		s.push(23);
		s.push(11);
		
		out.println("stack before 2 pops :: "+s);
		s.pop();
		s.pop();
		
		out.println("stack after 2 pops :: "+s);
	}
}
