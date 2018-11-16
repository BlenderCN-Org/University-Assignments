//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class Lab10e
{
	public static void main( String args[] ) throws IOException
	{
			int[][] m = {{1,0,0,0,1},
						{1,1,1,1,1},
					 	{0,1,1,0,1},
					 	{0,1,1,1,1},
						{0,0,0,0,1}};

		int path = Integer.MAX_VALUE;
		ShortestPathMaze Halo = new ShortestPathMaze(m);

		if(Halo.checkForExitPath(0,0,path) == true)
		{

		}
		else
		{
			System.out.println(Halo.toString());
		}


	}
}