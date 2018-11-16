import java.util.*;
import java.io.*;
import java.lang.System.*;

public class UILAge {

    public static void main(String [] args)throws IOException
    {
		Scanner fileReader = new Scanner (new File("AgeOut.txt"));

		int NumOfRepeat = fileReader.nextInt();
		int Young = 0;
		int Old = 0;
		int Mult = 0;

		int YoungTest = 0;
		int OldTest = 0;

		int y = 1;
		int Checker = 0;


		/*
		fileReader.nextLine();
		int Tester = fileReader.nextInt();
		*/

		for(int i = 0; i < NumOfRepeat; i++) //BigLoop
		{
			Young = fileReader.nextInt();

		//	System.out.println(Young);

			Old = fileReader.nextInt();

		//  System.out.println(Old);

			Mult = fileReader.nextInt();

		//	System.out.println(Mult);

			YoungTest = Young;

			OldTest = Old;

			Checker = 0;
			y = 1;



				while(Young <= 10)
				{
					if(Young * Mult == Old)
					{
						System.out.println("" + Young + " , " + Old);
						Checker++;
						Young = 20;
					}
					else
					{
						Young++;
						Old++;
					}
				}
				if(Checker == 0)
				{
					System.out.println("NEVER");
				}





		}

    }


}