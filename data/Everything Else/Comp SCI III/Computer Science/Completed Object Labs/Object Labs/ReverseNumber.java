//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;

public class ReverseNumber
{

   private int number;
   private int rev;

	//add constructors
	public ReverseNumber()
	{

	number = 0;

	}

	public ReverseNumber (int num)
	{

	number = num;

	}



	//add a set method

	public int setReverse(int num)
	{


	 rev = 0;


	while(num > 0)
	{

  	 rev = rev * 10 + num % 10 ;
   	num = num / 10;
	}


	return rev;



	}



	public int getReverse()
	{
		int rev=0;




		return rev;
	}

	//add  a toString///// NAILED IT
	public String toString()
	{
		return number + " looks like this revesed " + rev + "!\n";
	}

}