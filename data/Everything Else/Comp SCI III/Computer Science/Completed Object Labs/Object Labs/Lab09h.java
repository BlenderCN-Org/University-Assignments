//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;
import java.util.*;
public class Lab09h
{
	public static void main( String args[] )
	{
	Scanner kb = new Scanner(System.in);
		System.out.println("Tell me a number and I will tell you if it is a perfect number");
		int input = kb.nextInt();
		Perfect p = new Perfect();
	
		//add test cases
		p.setPerf(input);
		p.isPerfect(input);
		System.out.println(p.toString());
		
		
		
																
	}
}