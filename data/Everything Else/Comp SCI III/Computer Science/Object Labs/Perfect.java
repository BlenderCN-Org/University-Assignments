//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;

public class Perfect
{
   private int number;


	public Perfect()
	{
		number =0;
	}

	public Perfect(int Div)
	{
		number = Div;
	}




	public String getDivisors(int Divis)
	{
		String finalvalue = "";
			String isPerfect = "";

		for(int i=1; i<Divis; i++)
		{
			if(Divis%i == 0)
			{
				finalvalue = finalvalue + i + ",";
				isPerfect = isPerfect + i;
			}

			if(i == Divis)
			{
				isPerfect = isPerfect + "is a perfect number";
			}
			else
			{
				isPerfect = isPerfect + "is not a perfect number";
			}
		}
			return finalvalue + "and " + isPerfect;

	}





}


	//add a toString


