//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;
import java.util.*;
public class Lab09g
{
	public static void main( String args[] )
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("Type in a incypted message::");
			
		String incrypted = kb.nextLine();
		
		System.out.println("Now tell the code so I can display your messeage::");
		
		String code= kb.nextLine();
		
		StringRemover s = new StringRemover();
		
		s.setRemover(incrypted,code);
		System.out.println(s.toString());
		//add test cases
		
		
		
		
											
	}
}