//© A+ Computer Science
// www.apluscompsci.com

//queue add example

import static java.lang.System.*;
import java.util.Queue;
import java.util.LinkedList;

public class QueueAdd
{
	public static void main( String args[] )
	{
		Queue<Integer> queue;
		queue = new LinkedList<Integer>();
		queue.add(11);
		queue.add(10);
		queue.add(7);
		out.println(queue);
	}
}