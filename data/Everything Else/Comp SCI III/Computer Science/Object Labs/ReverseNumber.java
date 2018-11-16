
import java.util.*;
import static java.lang.System.*;

public class ReverseNumber
{
   private int number;
     private int rev;

	public ReverseNumber()
	{
		number = 0;
	}
/////////////////////////////////////////////////////////////////////////
	public ReverseNumber(int num)
	{
		number = num;
	}
/////////////////////////////////////////////////////////////////////////
	public int getReverse(int nums)
	{

		rev = 0;


			while(nums > 0)
				{
   				 	rev = rev*10 + nums % 10;
  				 	nums = nums/10;
				}

			return rev;

	}
/////////////////////////////////////////////////////////////////////////
	public String toString()
	{
		return rev + "is a number";
	}

/////////////////////////////////////////////////////////////////////////
}