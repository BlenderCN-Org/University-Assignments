//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.util.Scanner;
import static java.lang.System.*;

public class Maze
{
   private int[][] maze;
   private boolean workplz;

	public Maze()
	{

	}

	public Maze(int[][] m)
	{
		maze = m;
	}

	public boolean checkForExitPath(int r, int c)
	{
		boolean test = false;

		if(r >= 0 && c >= 0 && r < maze.length && c < maze[r].length && maze[r][c] == 1 )
		{
			//System.out.println("1st if loops");
				if(c == maze[r].length-1 && maze[r][c] == 1)
				{
					//System.out.println("2st if loops");
					test = true;
					workplz = true;
					return true;
				}
				else
				{
					//System.out.println("else loops");
					maze[r][c] = 0;
					checkForExitPath(r+1,c);
					checkForExitPath(r-1,c);
					checkForExitPath(r,c+1);
					checkForExitPath(r,c-1);
				}
		}

		return test;
	}

	public String toString()
	{
		String output="";

		if(workplz == true )
		{
			output = "Works";

		}
		else
		{
				output = "!Works";
		}
		return output;
	}
}