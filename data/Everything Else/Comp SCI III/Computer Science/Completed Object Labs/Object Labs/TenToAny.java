//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;

public class TenToAny
{
   private int base10;
   private int newBase;

	//add constructors
	public TenToAny()
	{
	
		base10 = 0;
		newBase = 0;
	}
	//add a set method
	public TenToAny(int ten,int newb)
	{
		base10 = ten;
		newBase = newb;
	
	}
	public void setNum(int b,int nb)
	{
		base10= b;
		newBase = nb;
	}
	public String getNewNum()
	{
		String newNum="";

while ( base10!= 0)
{
	int remainder = base10%newBase;
	base10 = base10/newBase;
	newNum=  remainder + newNum ;
		
	
}
		
		

		return newNum;
	}

	//add a toString method
	public String toString()
	{
		return base10+" base 10 is " +getNewNum()+ " in base " + newBase;
	}
	
	
}