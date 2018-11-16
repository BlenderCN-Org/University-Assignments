//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.util.Scanner;
import static java.lang.System.*;

public class ShortestPathMaze
{
   private int[][] maze;
   private int shortest;
   private boolean workplz;
   private int counter = 0;

	public ShortestPathMaze()
	{
	}

	public ShortestPathMaze(int[][] m)
	{

	}


	public boolean checkForExitPath(int r, int c, int path)
	{
		//System.out.println("1st if loops");
				if(c == maze[r].length-1 && maze[r][c] == 1)
				{
					//System.out.println("2st if loops");
					counter++;
					workplz = true;
					return true;
				}
				else
				{
					counter++;
					//System.out.println("else loops");
					maze[r][c] = 0;
					checkForExitPath(r+1,c,path);
					checkForExitPath(r-1,c,path);
					checkForExitPath(r,c+1,path);
					checkForExitPath(r,c-1,path);
				}
			return workplz;
	}

	public int getShortestPath()
	{
		return counter;
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