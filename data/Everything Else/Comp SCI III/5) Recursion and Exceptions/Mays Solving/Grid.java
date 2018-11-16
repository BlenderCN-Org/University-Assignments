//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class Grid
{
   private String[][] grid;

	public Grid()
	{

	}

	public Grid(int r, int c, String[] vals)
	{
		int row = r;
		int ccol = c;
		String val = "";
			val = val + vals;
	}

	public void setGrid(int r, int c, String[] vals)
	{

	}

	public int findMax(String val)
	{
		int count = 0;

		if(row >= 0 && col >= 0 && row <=grid.length-1 && col <= grid[r].length-1)
		{
			if(grid[r][c].equals("@"))
			{
				grid[r][c].equals("-");
				count++;
				findMax(r+1,c);
				findMax(r-1,c);
				findMax(r,c+1);
				findMax(r,c-1);
			}
		}

		return count;
	}

	private int findMax(int r, int c, String search)
	{
		return 0;
	}

	public String toString()
	{
		String output="";
		return output;
	}
}