 //© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;

public class Divisors
{
   private int number;
  private String divisors;

	//add constructors
	public Divisors()
	{
	number = 0;
	}
	
	public Divisors(int div)
	{
	 number = div;
	 
	}


	//add a set method
	public void setDiv(int num)
	{
		number = num ;
	}



	public String getDivisors(int num)
	{
	divisors = "";
		
		for(int i=1;i<=num;i++)
        {

            if(num%i==0)
            {
           divisors = divisors + i + ",";
            }
        }
	return divisors;
	}

	//add a toString
	public String toString()
		{
		return "These are the divisors" + divisors;
	}

	

}