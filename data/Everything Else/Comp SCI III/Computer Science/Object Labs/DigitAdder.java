
import java.util.*;
import static java.lang.System.*;

public class DigitAdder
{
   private int number;
	private int sum;
	public DigitAdder()
	{
		number = 0;
	}
/////////////////////////////////////////////////////////////////////////
	public DigitAdder(int Digi)
	{
	number = Digi;
	}
/////////////////////////////////////////////////////////////////////////
	public int getSum(int num)
	{
		sum=0;


		while(num > 0)
		{
		   sum = sum + num%10;
		   num = num/10;
		}

		return sum;
	}
/////////////////////////////////////////////////////////////////////////
	public String toString()
	{
		return number + " is a " + "\n";
	}

}