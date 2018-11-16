import java.io.*;
import java.util.*;


public class BlobSize {

	
		public static char [][] mat;
		public static int sum;
		
	public static void main(String[] args) throws IOException
	{
		Scanner fr = new Scanner(new File("blobsize.dat"));
		int count = fr.nextInt();
		fr.nextLine();
		int q = 0;
		
		while(count-->0)
		{
			
			mat = new char[fr.nextInt()][fr.nextInt()];
			
			
			 q = fr.nextInt();
			 fr.nextLine();
			 
			for(int i = 0; i<mat.length; i++)
				mat[i] = fr.nextLine().toCharArray();
			//fr.nextLine();
			

			
			
			
			
			while(q-->0)
			{

				meth(fr.nextInt()-1,fr.nextInt()-1);
				
				fr.nextLine();
				if(sum == 0)
					System.out.println("NO BLOB");
				else
					System.out.println(sum);
				

					sum =0;
				reset(0,0);
			
			
			
			}
		}
		
		
		
	}
	
	
	public static int meth(int y, int x)
	{
		if(y>=0&&x>=0&&y<mat.length&&x<mat[0].length&&mat[y][x] == '*')
		{
			sum++;
			mat[y][x] ='t';
			meth(y-1,x);
			meth(y+1,x);
			meth(y-1,x-1);
			meth(y-1,x+1);
			meth(y+1,x-1);
			meth(y+1,x+1);
			meth(y,x+1);
			meth(y,x-1);
			
		}
		
		
		
		return 60;
		
	}
	public static void reset(int y, int x)
	{
		if(y<mat.length&&x<mat[0].length)
		{
			if(mat[y][x]=='t')
				mat[y][x] = '*';
			
			reset(y+1,x);
			reset(y,x+1);
				
		}
	}

}
