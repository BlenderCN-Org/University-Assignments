//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;

class StringRemover
{
   private String sentence;
   private String remove;

	//add in constructors
	public StringRemover ()
	{
		sentence = "";
		remove = "";
	}
	public StringRemover( String sen,String r)
	{
		sentence = sen;
		remove = r;
	}
	

	public void setRemover(String s, String rem)
	{
		sentence = s;
		remove = rem;



	}

	public String removeStrings()
	{
		String cleaned = sentence;
		
/*
		use indexOf to look for more removals
		
		while you have more removals
		{
		   take out the current removal using substring
		   use indexOf to look for more removals
		}
*/
 int loc = sentence.indexOf(remove);
    while (loc > -1) {
      cleaned = cleaned.substring(0, loc) + cleaned.substring(loc + remove.length());
      loc = cleaned.indexOf(remove);
    }

		return cleaned;
	}

	public String toString()
	{
		return sentence+" is the sentence, and the string to remove is "+ remove+ " It looks like this " + removeStrings();
	}
}