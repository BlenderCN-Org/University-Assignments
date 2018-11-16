//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

/*
1 0 0 0 1
1 1 1 1 0
0 0 1 0 1
0 1 1 1 0
0 0 0 0 1
*/

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class Lab10d
{
	public static void main( String args[] ) throws IOException
	{
		int[][] m = {{1,0,0,0,1},
					 {1,1,1,1,1},
					 {0,1,1,0,1},
					 {0,1,1,1,1},
					 {0,0,0,0,1}};

		Maze Halo = new Maze(m);
		Halo.checkForExitPath(0,0);

		System.out.println(Halo.toString());
	}
}

//Not, r, c, StartingPoint, x, p, t, d,