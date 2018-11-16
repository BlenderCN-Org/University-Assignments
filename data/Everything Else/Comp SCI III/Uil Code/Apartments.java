import java.util.*;
import java.io.*;
import java.lang.*;
import java.lang.System.*;


public class Apartments
{

    public static void main (String [] args)throws IOException
    {
    	Scanner fr = new Scanner(new File("Apartments.txt"));

    	int Looper = fr.nextInt();
    	fr.nextLine();
    //	System.out.println (Looper);

    	int AptNum = 0;
    	int Counter = 0;
    	int Sum = 0;

    	String N = "";
    	String subN = "";

    	for(int i = 0; i < Looper; i++)
    	{
    		AptNum = fr.nextInt();
    		fr.nextLine();
    	//	System.out.println (AptNum);

			for(int x = 0; x <AptNum; x++)
			{
				N = fr.nextLine();
			//	System.out.println (N);
				subN = N.substring(N.indexOf(" ")+1);
				Sum += Integer.parseInt(subN);
			//	System.out.println (Sum);

				if(Integer.parseInt(subN) == 0)
				{
					Counter++;
				//	System.out.println (Counter);
				}
			}

			if((double)Sum/(AptNum-Counter) <=4)
			{
				System.out.println("Yes " + Counter);
			}
			else
			{
				System.out.println("No " + Counter);
			}

			Counter = 0;
			Sum = 0;

    	}
    }


}