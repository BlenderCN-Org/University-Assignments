//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;

public class Perfect
{
   private int number;
   private boolean perfect;
	//add constructors
	public Perfect()
	{
		number = 0;
		
	}
	public Perfect(int perf)
	{
		number = perf;
	}

	//add a set method
	public void setPerf(int num)
	{
		
	number = num;
		
	}

	public boolean isPerfect(int num)
	{
		int x = 0;
			
		for(int i=1;i <  num;i++)
        {

            if(num % i==0)
            {
            	
           x = x + i;
           
           	
           } 
         
        }
        
        if (x == num)
        {
        	perfect= true;
        	
        	return true;
      }
       else 
        	
   	  return false;
	
	}

	//add a toString
	public String toString()
	{
		if(perfect == true){
			
			return number + " is perfect";
		}
		else
			return number + " is not perfect";
	}
	
}