import java.util.*;
import static java.lang.System.*;



public class Lab09d
{
	public static void main( String args[] )
	{
		Scanner GetNum = new Scanner(System.in);


		System.out.println("Please enter a Number :: ");
		int UserInput = GetNum.nextInt();
		DigitAdder d = new DigitAdder();
		System.out.println("The Digit form of your number is:: " + d.getSum(UserInput));





	}
}