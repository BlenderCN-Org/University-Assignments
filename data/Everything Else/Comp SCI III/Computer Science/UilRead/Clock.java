import java.util.*;
import java.io.*;


public class Clock {

    public static void main(String args [])throws IOException
    {
		Scanner kb = new Scanner(new File("clock.dat"));

		int num = kb.nextInt();

		String time = "";
		int Inde = 0;

		int hour = 0;
		int min = 0;

		int hourMod = 0;

		String adj = "";

		for(int x = 0; x < num; x++)
		{
			time = kb.next();
		//	System.out.println (time);
			Inde = time.indexOf(":");
		//	System.out.println (Inde);

			hour = Integer.parseInt(time.substring(0,Inde));
			min = Integer.parseInt(time.substring(Inde+1));

			hour = 11-hour;
			min = 60-min;

			if(min == 0)
			{
				adj = "00";
			}

			if(min == 60)
			{
				hour++;
				adj = "00";
			}
			else
			{
				adj = min + "";
			}

			if(hour > 12)
			{
				hourMod = hour%12;
				hour = 0;
				hour += hourMod;
			}
			if(hour == 0 && adj.equals("00"))
			{
				hour = 12;
			}



			System.out.println(Math.abs(hour) + ":" + adj);

		}
    }


}