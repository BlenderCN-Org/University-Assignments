//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -
import java.util.*;
import static java.lang.System.*;

public class Lab09e
{
	public static void main( String args[] )
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("Type in a number and I the Computer God will tell you the divisors");
		int input = kb.nextInt();
		Divisors d = new  Divisors();
		d.getDivisors(input);
		System.out.println(d.toString());
		
		//add test cases






	}
}