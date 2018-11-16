import java.util.Scanner;

public class MyLibrary 
{

    public static int getRandNum( int n )
    {//This method will return an integer in the range [1,n]
    	return (int)(Math.random()*n)+1;
    }
    
    public static int getRandNum( int lo, int hi )
    {//This method will return an integer in the range [lo,hi]
    	return (int)(Math.random()*( hi-lo+1))+lo;
    }
    
    public static int getInt( String prompt )
    {
    	Scanner kb = new Scanner( System.in );
    	System.out.print(prompt +"  ");
 		int input = kb.nextInt();  	
    	return input;
    }
    
    public static double getDouble( String prompt )
    {
    	Scanner kb = new Scanner( System.in );
    	System.out.print(prompt +"  ");
 		double input = kb.nextDouble();  	
    	return input;
    }
    
    public static String getWord( String prompt )
    {
    	Scanner kb = new Scanner( System.in );
    	System.out.print(prompt +"  ");
 		String input = kb.next();  	
    	return input;
    }
    
    public static String getLine( String prompt )
    {
    	Scanner kb = new Scanner( System.in );
    	System.out.print(prompt +"  ");
 		String input = kb.nextLine();  	
    	return input;
    }
    
    public static double round ( double num, int dec )
    {
    	
    	return  (int)Math.round((num* Math.pow(10,dec)))/Math.pow(10,dec);
    	
    }
    
    
    
    
    
    
    
}