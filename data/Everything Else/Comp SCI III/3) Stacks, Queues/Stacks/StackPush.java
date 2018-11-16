//© A+ Computer Science
// www.apluscompsci.com

//stack push example

import static java.lang.System.*;
import java.util.Stack;

public class StackPush
{
	public static void main( String args[] )
	{
		Stack<Integer> s;
		s = new Stack<Integer>();
		s.push(88);
		s.push(23);
		s.push(11);
		out.println(s);
	}
}
