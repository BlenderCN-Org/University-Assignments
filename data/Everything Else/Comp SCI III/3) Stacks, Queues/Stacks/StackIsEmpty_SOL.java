//© A+ Computer Science
// www.apluscompsci.com

//Stack example isEmpty

import static java.lang.System.*;
import java.util.Stack;

public class StackIsEmpty_SOL
{
	public static void main( String args[] )
	{
		Stack<Integer> s;
		s = new Stack<Integer>();
		
		s.push(88);
		s.push(23);
		s.push(77);
		s.push(22);				
		s.push(11);
		
		while(!s.isEmpty())
		{
		   out.println(s.pop());
		}
	}
}