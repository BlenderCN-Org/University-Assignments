//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;

public class AtCounter
{
   private char[][] atMat;
   private int atCount;


	public AtCounter()
	{
		atCount=0;
		atMat = new char[][]{		{'@','-','@','-','-','@','-','@','@','@'},
									{'@','@','@','-','@','@','-','@','-','@'},
									{'-','-','-','-','-','-','-','@','@','@'},
									{'-','@','@','@','@','@','-','@','-','@'},
									{'-','@','-','@','-','@','-','@','-','@'},
									{'@','@','@','@','@','@','-','@','@','@'},
									{'-','@','-','@','-','@','-','-','-','@'},
									{'-','@','@','@','-','@','-','-','-','-'},
									{'-','@','-','@','-','@','-','@','@','@'},
									{'-','@','@','@','@','@','-','@','@','@'}};
	}

	public void countAts(int r, int c)
	{
		if(r >= 0 && c >= 0 && r <=atMat.length-1 && c <= atMat[r].length-1)
		{
			if(atMat[r][c] == '@')
			{
				atMat[r][c] = '-';
				atCount++;
				countAts(r+1,c);
				countAts(r-1,c);
				countAts(r,c+1);
				countAts(r,c-1);
			}
		}
	}

	public String toString()
	{
		String output="";
		output+=atCount+" @s connected.";
		return output;
	}
}