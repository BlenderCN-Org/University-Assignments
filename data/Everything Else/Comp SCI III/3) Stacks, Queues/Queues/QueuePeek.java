//© A+ Computer Science
// www.apluscompsci.com

//queue peek example

import static java.lang.System.*;
import java.util.Queue;
import java.util.LinkedList;

public class QueuePeek
{
	public static void main( String args[] )
	{
		Queue<Integer> queue;
		queue = new LinkedList<Integer>();
		queue.add(11);
		queue.add(7);
		out.println(queue);
		out.println(queue.peek());
		queue.remove();
		out.println(queue.peek());
		queue.remove();
		out.println(queue);
	}
}