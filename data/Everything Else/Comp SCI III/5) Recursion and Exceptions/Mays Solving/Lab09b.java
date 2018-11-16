import java.util.*;
import static java.lang.System.*;

public class Lab09b
{


	public static void main(String args[])
	{

		AtCounter Maze = new AtCounter();
		Scanner kb = new Scanner(System.in);

		System.out.println("Please Enter Two Numbers Between 0 and 9");
		int r = kb.nextInt();
		int c = kb.nextInt();


		Maze.countAts(r,c);
		System.out.println(Maze.toString());

	}

}