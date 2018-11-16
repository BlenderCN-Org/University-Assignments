import java.util.*;
import java.io.*;
import java.lang.System.*;


public class UILCandy {

    public static void main(String [] args)throws IOException
    {
		Scanner fileReader = new Scanner (new File("Candy.txt"));

		int CaseNum = fileReader.nextInt();
		int GroupNum = 0;
		int NumCheck = 1;
		int GroupSize = 0;


		ArrayList<Integer> Groups = new ArrayList<Integer>();


		for(int x = 0; x < CaseNum; x++)
		{
			NumCheck = 0;
			fileReader.nextLine();
			GroupNum = fileReader.nextInt();

			for(int i = 0; i < GroupNum; i ++)
			{
				Groups.add(fileReader.nextInt());
			}

			for(int p = 0; p < GroupNum; p++)
			{
				while(NumCheck % (Integer)Groups.get(p) != 0)
				{
					NumCheck++;
				}
			}

			//System.out.println(NumCheck);



		}


    }


}