import java.util.*;
import java.io.*;
import java.lang.*;

public class CatchingExceptions {

    public static void main (String [] args) throws Exception
    {
    	Scanner kb = new Scanner(System.in);

    	System.out.println ("Enter Two Numbers:: ");
    	int a = kb.nextInt();
    	int b = kb.nextInt();
    	int c = 0;

    	try
    	{
    		c = a/b;
    	}
    	catch(Exception e)
    	{
    		System.out.println ("ERROR:: " + a + "/" + b + " = ERROR// |" + " CANNOT DIVIDE BY 0!");
    	}

    }

    public void myMethod(double d) throws Exception
    {

    }

}