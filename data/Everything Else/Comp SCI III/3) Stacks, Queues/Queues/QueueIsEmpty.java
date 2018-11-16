//© A+ Computer Science
// www.apluscompsci.com

//queue isempty example

import static java.lang.System.*;
import java.util.Queue;
import java.util.LinkedList;

public class QueueIsEmpty
{
	public static void main( String args[] )
	{
		Queue<Integer> queue;
		queue = new LinkedList<Integer>();
		queue.add(11);
		queue.add(10);
		queue.add(7);

		while(!queue.isEmpty())
		{
			out.println(queue.remove());
		}
	}
}