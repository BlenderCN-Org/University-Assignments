//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;

public class LetterRemover
{
   private String sentence;
   private char lookFor;

	public LetterRemover()
	{
		//call set
	}

	//add in second constructor



	public void setRemover(String s, char rem)
	{
		sentence = s;
		lookFor = rem;
	}

	public String removeLetters()
	{
		int loc = sentence.indexOf(lookFor)

		while
		{
   		 	sentence.substring(lookFor)
   			loc = sentence.indexOf(lookFor)
		}
	}

	public String toString()
	{
		return sentence + " - letter to remove " + lookFor;
	}
}