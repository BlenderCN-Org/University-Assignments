import java.util.*;
import static java.lang.System.*;


public class Lab09h
{
	public static void main( String args[] )
	{
			Scanner GetNum = new Scanner(System.in);

		System.out.println("Please enter a Number :: ");
		int UserInput = GetNum.nextInt();
		Perfect d = new Perfect();
		System.out.println("The Divisors from of your number are:: " + d.getDivisors(UserInput));





	}
}