//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -
import java.util.*; 

import static java.lang.System.*;

public class Lab09f
{
	public static void main( String args[] )
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("Tell me a sentence");
		
		String USentence = kb.nextLine();
		
		System.out.println("Tell me a letter you would like to remove");
		
		String Uword = kb.nextLine();
		LetterRemover l = new LetterRemover();
		
		//add test cases
		l.setRemover(USentence,Uword);
	//	System.out.println(l.setRemover(USentence,Uword));
		System.out.println(l.toString());
		
		
											
	}
}