import java.util.*;
import static java.lang.System.*;


public class Lab09e
{
	public static void main( String args[] )
	{
		Scanner GetNum = new Scanner(System.in);


		System.out.println("Please enter a Number :: ");
		int UserInput = GetNum.nextInt();
		Divisors d = new Divisors();
		System.out.println("The Divisors from of your number are:: " + d.getDivisors(UserInput) + "and " + d.Perfect());





	}
}