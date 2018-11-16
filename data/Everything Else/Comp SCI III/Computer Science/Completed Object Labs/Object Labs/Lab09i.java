//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -
import java.util.*;
import static java.lang.System.*;

public class Lab09i
{
	public static void main( String args[] )
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("Tell me a number in base 10");
			
		int input = kb.nextInt();
		
		System.out.println("Now what would you like to convert this too.");
			
		int base = kb.nextInt();
		
		TenToAny t = new TenToAny();
		
		t.setNum(input,base);
		 System.out.println(t.toString());
		//add test cases
		
		
		
		
	}
}