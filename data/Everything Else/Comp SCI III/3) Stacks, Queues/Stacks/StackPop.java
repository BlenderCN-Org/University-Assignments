//© A+ Computer Science
// www.apluscompsci.com

//stack pop example

import static java.lang.System.*;
import java.util.Stack;

public class StackPop
{
	public static void main( String args[] )
	{
		Stack<Integer> s;
		s = new Stack<Integer>();
		s.push(88);
		s.push(23);
		s.push(11);
		out.println("stack before pop :: "+s);
		s.pop();
		out.println("stack after pop :: "+s);
	}
}
