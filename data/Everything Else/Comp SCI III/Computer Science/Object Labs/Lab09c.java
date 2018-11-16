import java.util.*;
import static java.lang.System.*;

public class Lab09c
{
	public static void main( String args[] )
	{
		Scanner GetNum = new Scanner(System.in);


		System.out.println("Please enter a Number Grade:: ");
		int UserInput = GetNum.nextInt();
		ReverseNumber r = new ReverseNumber();

			System.out.println("The Reverse form of your number is:: " + r.getReverse(UserInput));






	}
}