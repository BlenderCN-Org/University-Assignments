//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -
import java.util.*;
import static java.lang.System.*;

public class Lab09d
{
	public static void main( String args[] )
	{
		//add test cases
		Scanner kb =  new Scanner (System.in);
		System.out.println("Type in a number ::");
		int input =	kb.nextInt();

		DigitAdder d = new DigitAdder();
		d.setSum(input);



		System.out.println(input+d.toString());







	}
}