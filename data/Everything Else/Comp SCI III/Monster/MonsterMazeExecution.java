/**
 * @(#)MonsterMazeExecution.java
 *
 *
 * @author
 * @version 1.00 2015/11/12
 */


import java.io.*;
import java.util.*;
import static java.lang.System.*;
import java.lang.*;

public class MonsterMazeExecution
{
	private static String[][] m;

    public static void main( String args[] ) throws IOException
	{
		int Not = 0;
		int r = 0;
		int c = 0;
		int StartingPoint = 0;

		String Line = "";

		Scanner kb = new Scanner(new File("MonsterMaze.txt"));

		File NaVi = new File("Maze.out");
		NaVi.createNewFile();
		FileWriter out = new FileWriter(NaVi);

		MonsterMaze iBuyPower = new MonsterMaze();

		Not = kb.nextInt();

		kb.nextLine();

		for(int Fnatic = 0; Fnatic < Not; Fnatic++)
		{

			r=kb.nextInt();
			//	System.out.println(r);
			//	System.out.println("");
			c=kb.nextInt();
			//	System.out.println(c);
			//	System.out.println("");

				m = new String[r][c];

			kb.nextLine();

			for(int x = 0; x < r; x++)
			{
				Line = kb.nextLine();
				for(int p = 0; p < c; p++)
				{
					m[x][p] = (Line.charAt(p)) + "";
					if(Line.charAt(p) == 'S')
					{
						StartingPoint = p;
					}
				//	System.out.print(m[x][p]);
				}
			//	System.out.println("");
			}

		//	System.out.println(m.length);
		//	System.out.println(m[0].length);
		//	System.out.println("");

			for(int t = 0; t < m.length; t++) // t is rows
			{
				for(int d = 0; d < m[0].length; d++) // d is cols
				{
					if(m[t][d].equals("M"))
					{
						if(t>0 && d>0 && t < m.length-1 && d < m[t].length-1) //Anywhere not on the Edges
						{
							m[t][d] = "X";
							m[t+1][d] = "X";
							m[t-1][d] = "X";
							m[t][d+1] = "X";
							m[t][d-1] = "X";
						}
						else if(t==0 && d==0) /// Top Left
						{
							m[t][d] = "X";
							m[t-1][d] = "X";
							m[t][d+1] = "X";
						}
						else if(t==0 && d==m[0].length-1) // Top Right
						{
							m[t][d] = "X";
							m[t+1][d] = "X";
							m[t][d-1] = "X";
						}
						else if(t==m.length && d == 0) // Bottom Left
						{
							m[t][d] = "X";
							m[t+1][d] = "X";
							m[t][d+1] = "X";
						}
						else if(t==m.length && d==m[0].length) // Bottom Right
						{
							m[t][d] = "X";
							m[t+1][d] = "X";
							m[t][d-1] = "X";

						}
						else if(!(t>0) && d>0 && t < m.length && d < m[t].length) // On the Upper Edge
						{
							m[t][d] = "X";
							m[t+1][d] = "X";
							m[t][d+1] = "X";
							m[t][d-1] = "X";
						}
						else if(t>0 && !(d>0) && t < m.length && d < m[t].length) // On the Left Edge
						{
							m[t][d] = "X";
							m[t+1][d] = "X";
							m[t-1][d] = "X";
							m[t][d+1] = "X";
						}
						else if(t>0 && d>0 && t<m.length && !(d<m[0].length-1)) // On the Right Edge
						{
							m[t][d] = "X";
							m[t+1][d] = "X";
							m[t-1][d] = "X";
							m[t][d-1] = "X";
						}
						else if(t>0 && d>0 && !(t<m.length) && d<m[0].length-1) //On the Bottom Edge
						{
							m[t][d] = "X";
							m[t+1][d] = "X";
							m[t][d+1] = "X";
							m[t][d-1] = "X";
						}

					}
				}
			}


				for(int we = 0; we < m.length; we++)
				{
					for(int ew = 0; ew < m[0].length; ew++)
					{
					//	System.out.print(m[we][ew]);
					}
				//	System.out.println("");
				}

		iBuyPower.setMaze(m);
		iBuyPower.checkForExitPath(0,StartingPoint);
		out.write("\r\n" + iBuyPower.toString());
		out.flush();

		StartingPoint = 0;

		m = null;


	//	kb.nextLine();



		}


		/////////////////////////////////////////////////////////////////////////////////////////////////// - Now Actual Code


		out.close();
	}

}