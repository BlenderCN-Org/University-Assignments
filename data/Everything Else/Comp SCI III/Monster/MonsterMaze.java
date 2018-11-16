import java.util.*;
import java.io.*;
import java.lang.*;
import static java.lang.System.*;

public class MonsterMaze
{

	private String[][] maze;
	private boolean ExitFound;


	public MonsterMaze()
	{

	}

	public MonsterMaze(String[][] m)
	{
		maze = m;
	}

	public void setMaze(String[][] m)
	{
		maze = m;
	}

	public boolean checkForExitPath(int r, int c)
	{
		boolean JuanFound= false;

		if(r >= 0 && c >= 0 && r < maze.length && c < maze[r].length && !(maze[r][c].equals("X") || maze[r][c].equals("0"))) // If **In Bounds**
		{
			if(maze[r][c].equals("E"))
			{
				JuanFound = true;
				ExitFound = true;
				return true;
			}
			else
			{
				maze[r][c] = "0";
				checkForExitPath(r+1,c);
				checkForExitPath(r-1,c);
				checkForExitPath(r,c+1);
				checkForExitPath(r,c-1);
			}
		}

		return JuanFound;
	}

	public String toString()
	{
		String output="";

		if(ExitFound == true)
		{
			output = "EXIT";
		}
		else
		{
			output = "NO EXIT";
		}
		ExitFound = false;
		return output;

	}


}