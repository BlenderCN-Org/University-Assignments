import java.util.*;
import java.io.*;
import java.lang.*;
import java.lang.System.*;


public class BlobTop
{

private static String m [][];

    public static void main (String [] args)throws IOException
    {
		Scanner fr = new Scanner(new File("blobtop.txt"));

		int Looper = fr.nextInt();
    	fr.nextLine();

    	String mLine = "";

    	int opX = 0;
    	int opY = 0;

    	for(int i = 0; i < Looper; i++)
    	{
    		int r = 0;
	    	r = fr.nextInt();
	    	int c = 0;
	    	c = fr.nextInt();
	    	int s = 0;
	    	s = fr.nextInt();
	   		//System.out.println (r + " " + c + " " + s);

			m = new String[r][c];
			fr.nextLine();

	    	for(int x = 0; i < r; i++) // Fills up Matrix
	    	{
				mLine = fr.nextLine();
				//System.out.println (mLine);
				for(int d = 0; d < c; d++)
				{
					m[x][d] = mLine.charAt(d) + "";
					//System.out.print(m[x][d]);
				}
				//System.out.println();
	    	}
	    	fr.nextLine();

	    	for(int p = 0; p < s; p++)
	    	{
				opX = fr.nextInt();
				opY = fr.nextInt();


	    	}

    	}

    }

    public void Solve(int r, int c)
    {

    }



}