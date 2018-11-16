

public class LibraryRunner
{

    public static void main ( String [] args )
    {
    	
    	int number = MyLibrary.getInt("Enter a number::  ");
    	
    	String name = MyLibrary.getWord("Enter your name:: ");
    	
    	int ranNum = MyLibrary.getRandNum( 1, 40 );
    	
    	System.out.println(  );
    	System.out.println(  );
    	System.out.println( number );
    	System.out.println( name );
    	System.out.println( ranNum );
    	
    	
    	
    	
    	
    }
    
}