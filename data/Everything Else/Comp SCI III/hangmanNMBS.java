import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;





public class hangmanNMBS {

public static String correctguess (String a)
{return a+"test";}

public static String hang1(int x)
{
	String hangman="";
	if(x==0)
{
String line1=" ------";
String line2= " |    |";
String line3= "      |";
String line4= "      |";
String line5= "      |";
String line6= "      |";
String line7= "=======";
 hangman=line1+"\n"+line2+"\n"+line3+"\n"+line4+"\n"+line5+"\n"+line6+"\n"+line7+"\n";
}
	if(x==1)
{
String line1=" ------";
String line2= " |    |";
String line3= " 0    |";
String line4= "      |";
String line5= "      |";
String line6= "      |";
String line7= "=======";
 hangman=line1+"\n"+line2+"\n"+line3+"\n"+line4+"\n"+line5+"\n"+line6+"\n"+line7+"\n";
}
	if(x==2)
{
String line1=" ------";
String line2= " |    |";
String line3= " 0    |";
String line4= " |    |";
String line5= "      |";
String line6= "      |";
String line7= "=======";
 hangman=line1+"\n"+line2+"\n"+line3+"\n"+line4+"\n"+line5+"\n"+line6+"\n"+line7+"\n";
}
	if(x==3)
{
String line1=" ------";
String line2= " |    |";
String line3= " 0    |";
String line4= "-|    |";
String line5= "      |";
String line6= "      |";
String line7= "=======";
 hangman=line1+"\n"+line2+"\n"+line3+"\n"+line4+"\n"+line5+"\n"+line6+"\n"+line7+"\n";
}
	if(x==4)
{
String line1=" ------";
String line2= " |    |";
String line3= " 0    |";
String line4= "-|-   |";
String line5= "      |";
String line6= "      |";
String line7= "=======";
 hangman=line1+"\n"+line2+"\n"+line3+"\n"+line4+"\n"+line5+"\n"+line6+"\n"+line7+"\n";
}
	if(x==5)
{
String line1=" ------";
String line2= " |    |";
String line3= " 0    |";
String line4= "-|-   |";
String line5= "/     |";
String line6= "      |";
String line7= "=======";
 hangman=line1+"\n"+line2+"\n"+line3+"\n"+line4+"\n"+line5+"\n"+line6+"\n"+line7+"\n";
}
	if(x==6)
{
String line1=" ------";
String line2= " |    |";
String line3= " 0    |";
String line4= "-|-   |";
String line5= "/\\    |";
String line6= "      |";
String line7= "=======";
 hangman=line1+"\n"+line2+"\n"+line3+"\n"+line4+"\n"+line5+"\n"+line6+"\n"+line7+"\n";
}

return hangman;
}



    public hangmanNMBS() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  throws IOException
    	{
       		Scanner fileReader = new Scanner (new File("hangmanWordList.dat"));
       		ArrayList<String> wordlist = new ArrayList<String>();
       while(fileReader.hasNextLine())
       {
       	wordlist.add(fileReader.nextLine());
       }
       String key = wordlist.get((int)(Math.random()*(10)));
      // System.out.println(key);

		//
	//	System.out.println("pick a letter or Phrase to guess");
	//	Scanner kb = new Scanner (System.in);
	//	String guess= kb.nextLine();
		//System.out.println(guess);
		ArrayList<String> alpha = new ArrayList<String>();
		alpha.add("A");
		alpha.add("B");
		alpha.add("C");
		alpha.add("D");
		alpha.add("E");
		alpha.add("F");
		alpha.add("G");
		alpha.add("H");
		alpha.add("I");
		alpha.add("J");
		alpha.add("K");
		alpha.add("L");
		alpha.add("M");
		alpha.add("N");
		alpha.add("O");
		alpha.add("P");
		alpha.add("Q");
		alpha.add("R");
		alpha.add("S");
		alpha.add("T");
		alpha.add("U");
		alpha.add("V");
		alpha.add("W");
		alpha.add("X");
		alpha.add("Y");
		alpha.add("Z");
		ArrayList<String>keyguess = new ArrayList<String>();
		    	for(int i=0;i<key.length();i++)
			    	{
			    		keyguess.add("_");
			    	}
		int wrong=0;
		System.out.println(hang1(wrong));
		System.out.println("UNUSED LETTERS");
		System.out.println(alpha);
		System.out.println(keyguess);
		while(wrong<6)
		{


			System.out.println("pick a letter or Phrase to guess");
			Scanner kb = new Scanner (System.in);
			String guess= kb.nextLine();
			guess=guess.toUpperCase();

			if(guess.length()>=2)
				{
				if (key.compareTo(guess)==0)
					{
					System.out.println("you win");
					wrong=8; //finish game here
					}
		    	else {
		    		wrong++;
		    	System.out.println(hang1(wrong));
				System.out.println("UNUSED LETTERS");
				System.out.println(alpha);
				System.out.println(keyguess);
		    	}

				}
			else
				{
				int x= alpha.indexOf(guess);
				alpha.set(x,"_");
		    	ArrayList<String> keychopped= new ArrayList<String>();

		    	for(int i=0;i<key.length();i++)
			    	{
			    		keychopped.add(key.substring(i,i+1));
			    	}

		    	int c=0;
		    	int w=0;

		    	for (int i=0;i<keychopped.size();i++)
			    	{
			    	//	System.out.println(guess);
			    	//	System.out.println(keychopped.get(i));
			    		if(guess.compareTo(keychopped.get(i))==0)
			    		{
			    			c++;
			    			System.out.println("correct");
			    			keyguess.set(i,guess);

			    			//System.out.println(correctguess(keychopped.get(i)));
			       		}
			       		else
			       		{w++;}
			    	}
				if (c>=1)
					{
						System.out.println(hang1(wrong));
						System.out.println("UNUSED LETTERS");
						System.out.println(alpha);
						System.out.println(keyguess);
					}
				else if (w>=1)
					{
						wrong++;
						System.out.println(hang1(wrong));
						System.out.println("UNUSED LETTERS");
						System.out.println(alpha);
						System.out.println(keyguess);

					}
					if (keyguess.indexOf("_")==-1)
						{	System.out.println("you win");
							wrong=8;

						}

		    	}
		}
		System.out.println("\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"game over");
    }
}
