//© A+ Computer Science
// www.apluscompsci.com

//new for loop example two


import static java.lang.System.*;
import java.util.ArrayList;

public class NewForTwo
{
	public static void main(String args[])
	{
		ArrayList<Integer>  list  = new ArrayList<Integer>();
		list.add(3);
		list.add(78);
		list.add(8);
		list.add(7);

		for (Integer number : list)
		   System.out.print(number + " ");

      out.println();

		for (int number : list)		//same as above
		   System.out.print(number + " ");

      out.println("\n"+list);
	}
}