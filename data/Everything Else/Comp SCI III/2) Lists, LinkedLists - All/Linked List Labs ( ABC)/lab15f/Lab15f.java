//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import java.util.*;
import static java.lang.System.*;

class LinkTest
{
	private BasicList list;

	public LinkTest()
	{
		list = new BasicList();
	}

	public LinkTest(int[] nums)
	{
		list = new BasicList();
		for(int num : nums)
		{
			list.add(num);
		}
	}

	public double getSum(  )
	{
		double total=0;
		for(int n=0; n<list.size(); n++)
			total=total+(Integer)list.get(n);
		return total;
	}

	public double getAvg(  )
	{
		return getSum()/list.size();
	}

	public Comparable getLargest()
	{
		Comparable largest=null;





		return largest;
	}

	public Comparable getSmallest()
	{
		Comparable smallest = null;






		return smallest;
	}

	public String toString()
	{
		String output="";
		output+="SUM:: "+getSum()+"\n";
		output+="AVERAGE:: "+getAvg()+"\n";
		output+="SMALLEST:: " +getSmallest()+"\n";
		output+="LARGEST:: "+getLargest()+"\n";
		return output;
	}
}

public class Lab15f
{
	public static void main ( String[] args )
	{
		LinkTest test = new LinkTest(new int[]{4,5,6,7,8,9,10,11,12,13});
		out.println(test);

		test = new LinkTest(new int[]{24,75,86,37,82,94,111,82,43});
		out.println(test);

		test = new LinkTest(new int[]{0,4,5,2,1,4,6});
		out.println(test);
	}
}