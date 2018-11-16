import java.util.*;
import java.io.*;
import java.lang.*;


public class BettingCSGO {

    public static void main (String [] args)
    {
    	Scanner kb = new Scanner(System.in);

		double max = 0.0;
		int maxRounds = 0;

    	System.out.println ("Starting Money?");
    	double total = kb.nextInt();
    	double staticTotal = total;

    	int rTotal = 0;
    	double mTotal = 0;

    	int x =0;
    	double y = 1.0;
    	double tempTot = total;
    	while(tempTot > 0.0)
    	{
    		tempTot -= y-(y*0.05);
			y *= 2.0;
			x++;
    	}

    	System.out.println (x);

    	//System.out.println ("NumRounds per Run:: ");
    //	int num = kb.nextInt();

    	ArrayList<Integer> rList = new ArrayList<Integer>();
    	ArrayList<Double> mList = new ArrayList<Double>();

    	for(int i = 0; i < 100000; i++)
    	{
    			double amn = 1;
				int round = 0;
    			double randomVal = 0.0;

    		for(int t = 0; t < x; t++)
	    	{
				randomVal = Math.random();
				//System.out.println (randomVal);

				if(total > 0)
				{
					if(randomVal < .5) // Winning
					{
						total += amn-(amn*0.05);
						amn = 1.0;
						round++;
						//System.out.println ("WIN");
					}
					else // Losing
					{
						total -= amn-(amn*0.05);
						amn *= 2.0;
						round++;
						//System.out.println ("LOSS");
					}

					//System.out.println (total);
					//System.out.println ("-------------------------------");

					if(total > max)
					{
						max = total;
						maxRounds = round;
					}
				}
				else
				{
					//System.out.println ("You broke out after " + round + " rounds.");
					t = x;
				}
	    	}
	    	rList.add(maxRounds);
	    	mList.add(max);
	    	maxRounds = 0;
	    	total = staticTotal;
	    	max = 0.0;
    	}

    	for(int u = 0; u < rList.size(); u++)
    	{
    		rTotal += rList.get(u);
    	}

    	for(int u = 0; u < mList.size(); u++)
    	{
    		mTotal += rList.get(u);
    	}

    	rTotal /= rList.size();
    	mTotal /= mList.size();

    	System.out.println ("The best number of rounds to bet on with $" + staticTotal + " is " + rTotal);
    	System.out.println ("Best Gains:: " + mTotal);

    	//System.out.println ("Your new total:: " + (total) + " after " + round + " rounds.");
    }


}