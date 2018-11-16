//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import java.util.LinkedList;
import static java.lang.System.*;

public class Lab15a
{
	public static void main ( String[] args )
	{
		LinkTest test = new LinkTest(new int[]{4,5,6,7,8,9,10,11,12,13});
		out.println(test);

		test = new LinkTest(new int[]{24,75,86,37,82,94,111,82,43});
		out.println(test);

		test = new LinkTest(new int[]{0,4,5,2,1,4,6});
		out.println(test);
	}
}