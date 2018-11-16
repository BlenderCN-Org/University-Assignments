/**
 * @(#)Spring.java
 *
 *
 * @author
 * @version 1.00 2015/11/5
 */
import java.util.*;
import java.io.*;
import java.text.*;


public class Spring {

    public static void main(String args [])throws IOException
    {
		Scanner kb = new Scanner(new File("spring.dat"));

		NumberFormat round = NumberFormat.getNumberInstance();
		round.setMaximumFractionDigits(2);
		round.setMinimumFractionDigits(2);

		int num = kb.nextInt();
		kb.nextLine();
		for(int x = 0; x < num; x++)
		{
			String name = kb.next();
			int runs = kb.nextInt();
			kb.nextLine();
			double raise = 0.0;
			for(int i = 0 ; i < runs; i++)
			{
				String temp = kb.nextLine();
				String[] add = temp.split(" ");
				raise += addT(add);
			}
			String temp = round.format(raise);
			if(Double.parseDouble(temp) < 500)
				System.out.println (name + " $" + round.format(raise));
			else
				System.out.println (name + " OVER $" + round.format(raise-500.0));
		}

    }

    public static double addT(String[] add){
    	double ret = 0.0;
    	for(int i = 0; i < add.length ; i++){
    		if(add[i].substring(0,1).equals("P"))
    			ret += (Double.parseDouble(add[i].substring(1))*14*0.48);
    		else if(add[i].substring(0,1).equals("T"))
    			ret += (Double.parseDouble(add[i].substring(1))*15*0.45);
    		else
    			ret += Integer.parseInt(add[i].substring(1));
    	}
    	return ret;
    }


}