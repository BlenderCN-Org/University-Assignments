//© A+ Computer Science
// www.apluscompsci.com

//pq isempty example

import static java.lang.System.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class PQueueIsEmpty
{
	public static void main( String args[] )
	{
		PriorityQueue<Integer> pQueue;
		pQueue = new PriorityQueue<Integer>();
		pQueue.add(11);
		pQueue.add(10);
		pQueue.add(7);

		while(!pQueue.isEmpty())
		{
		   out.println(pQueue.remove());
		}
	}
}