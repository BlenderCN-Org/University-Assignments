//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;

public class DigitAdder
{
   private int number;
   private int sum;

	//add constructors
	public DigitAdder()
	{
		 number = 0;


	}
	public DigitAdder(int num)
	{
		number = num;
	}

	//add a set method

	public int setSum(int num)
	{
	 sum = 0;



	while(num > 0)
	{
  	sum = sum + num % 10;

   	num = num / 10;

	}

	return sum;

	}


	public int getSum()
	{
		int sum=0;




		return sum;
	}

	//write  to toString method
	public String toString()
	{
		return " and this is the sum " + sum;
	}

}