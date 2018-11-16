//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;

public class LetterRemover
{
   private String sentence;
  // private char lookFor;
  private String lookFor;
   
   public LetterRemover()
   {
   	sentence ="";
   	lookFor = "";
   
   }

	public LetterRemover(String s,String rem)
	{
		//call set
		sentence=s;
		lookFor = rem;
	
	}

	//add in second constructor

	
	public void setRemover(String s, String rem)
	{
		sentence = s;
		lookFor = rem;
	}

	public String removeLetters()
	{
		
		String cleaned = sentence;
		//Algorithm
		int loc = cleaned.indexOf(lookFor);
		
	
		
		for(int i = 0; i<cleaned.length();i++)
		{
	 //  	take out the letter ( substring )
		 if (i == loc)
	 		{
	 		cleaned = cleaned.substring(0,i)+ cleaned.substring(i+1,cleaned.length());
	 		loc = cleaned.indexOf(lookFor);
	  	
	 		}
		
	  	
	  	
		}
		



		return cleaned;
	}

	public String toString()
	{
		return removeLetters()+ " - letter to remove " + lookFor;
	}
}