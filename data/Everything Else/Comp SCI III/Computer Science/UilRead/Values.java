import java.util.*;
import java.io.*;


public class Values {

     public static void main(String args [])throws IOException
    {
		Scanner kb = new Scanner(new File("values.dat"));

		int Inde = 0;
		int ArrNum = 0;
		int max = Integer.MAX_VALUE;
		int maxCount = 0;

		int sum = 0;
		char charCheck;

		String arNum = "";
		String arAdd = "";
		ArrayList<String> ar = new ArrayList<String>();
		ArrayList<String> arTwo = new ArrayList<String>();

		int times = kb.nextInt();
		kb.nextLine();
		String test = "";

		for(int x = 0; x < times; x++)
		{
			test = kb.nextLine();
			sum  = 0;

			for(int i = 0; i < test.length(); i++)
			{
				charCheck = test.charAt(i);
			//	System.out.println((int)charCheck);

				if((charCheck != ' '))
				{
					sum += ((int)charCheck)-64;
				//	System.out.println("Sum:: " + sum);
				}
				else
				{
					sum += 0;
				//	System.out.println("Sum:: " + sum);
				}

			}

			arNum = sum + "";
			arAdd = arNum + " " + test;
			//System.out.println (arAdd);
			ar.add(arAdd);
	 }


	for(int o = 0; o < ar.size(); o++)
	{
		for(int t = o; t < ar.size(); t++)
		{
			Inde = ar.get(t).indexOf(" ");
			ArrNum = Integer.parseInt(ar.get(t).substring(0,Inde));

			if(ArrNum < max)
			{
				max = ArrNum;
				maxCount = t;
			}

		}
			ar.remove(maxCount);
			arTwo.add(ar.get(maxCount));
	}


	for(int w = 0; w < arTwo.size(); w++)
	{
		System.out.println(arTwo.get(w));
	}





}

}