//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;

public class Divisors
{
   private int number;


	public Divisors()
	{
		number =0;
	}

	public Divisors(int Div)
	{
		number = Div;
	}




	public String getDivisors(int Divis)
	{
		String finalvalue = "";

		for(int i=1; i<Divis; i++)
		{

			if(Divis%i == 0)
			{
				finalvalue = finalvalue + i + ",";

			}
		}

		return finalvalue;
	}

	//add a toString


}