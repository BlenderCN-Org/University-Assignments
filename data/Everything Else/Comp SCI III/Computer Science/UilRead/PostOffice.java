/**
 * @(#)PostOffice.java
 *
 *
 * @author
 * @version 1.00 2015/11/5
 */
import java.util.*;
import java.io.*;
import java.text.*;


public class PostOffice {

    public static void main(String args [])throws IOException
    {
		Scanner kb = new Scanner(new File("postoffice.dat"));

		NumberFormat round = NumberFormat.getNumberInstance();
		round.setMaximumFractionDigits(2);
		round.setMinimumFractionDigits(2);

		int num = kb.nextInt();
		double tot = 0.0;
		kb.nextLine();
		for(int x = 0; x < num; x++)
		{
			Scanner read = new Scanner(kb.nextLine());
			while(read.hasNext())
				tot += read.nextDouble();
	//		System.out.println (tot);
			String temp = round.format(tot);
			if(Double.parseDouble(temp)<5.00)
				System.out.println ("OK");
			else
				System.out.println ("OVERWEIGHT");
			tot = 0.0;
		}

    }

    public static String addT(String[] x){
    	NumberFormat round = NumberFormat.getNumberInstance();
		round.setMaximumFractionDigits(2);
		round.setMinimumFractionDigits(2);
    	double tot = 5.00;
    	double temp = 0.0;
    	for(int i = 0; i < x.length ; i++){
    		temp += Double.parseDouble(x[i]);
    	}
    	String testpp = round.format(temp);
    	return Double.parseDouble(testpp) < tot ?"OK":"OVERWEIGHT";
    }

}