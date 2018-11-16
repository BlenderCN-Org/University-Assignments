
import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class StackLabs {

    public static void main (String [] args)
    {
		StacksC();
    }

    public static void StacksA()
    {
    	Stack<String> s = new Stack<String>();
    	s.add("A");
    	s.add("X");
		s.add("G");


    	System.out.println(s);

    	while(!s.isEmpty())
    	{
			System.out.println(s.pop());
    	}
    }

    public static void StacksB()
    {
    	Stack<String> s = new Stack<String>();
    	s.add("{");
    	s.add("a");
    	s.add("[");
    	s.add("t");
    	s.add("j");
    	s.add("]");
    	s.add("y");
    	s.add("l");
    	s.add("}");

    	String t1 = "";
    	String t2 = "";
    	String ImNotGoingToCollege = "";

    	Stack<String> x = new Stack<String>();

    	while(!s.isEmpty())
    	{

    		t1 = s.peek();

    		if(t1.equals("{") || t1.equals("[") || t1.equals("(") )
    		{
    			x.add(t1);
    		}
    		else if(t1.equals("}") || t1.equals("]") || t1.equals(")"))
    		{
    			if(!s.isEmpty())
    			{
    				if(x.pop().equals(t1))
    				{
    				}
    				else
    				{
    					{
    						s.pop();
    					}

    					ImNotGoingToCollege = "Incorrect";
    					System.out.println(ImNotGoingToCollege);
    				}
    			}
    		}

    	}

    	if(ImNotGoingToCollege.equals("Incorrect"))
    	{

    	}
    	else
    	{
    		System.out.println("Correct");
    	}


    }

    public static void StacksC()
    {
    	//2 7 + 1 2 + +

		Stack<String> a = new Stack<String>();

    	Stack<String> s = new Stack<String>();
    	s.add("2");
    	s.add("7");
    	s.add("+");
    	s.add("1");
    	s.add("2");
    	s.add("+");
    	s.add("+");

    	double numJuan = 0;
    	double numTwo = 0;

    	while(!s.isEmpty())
    	{
	    	if(!(s.peek().equals("+") || s.peek().equals("-") || s.peek().equals("*") || s.peek().equals("/"))) //Checks to see if its a num or an oper
	    	{
	    		a.push(s.pop());
	    	}
	    	else if(a.size() > 1) //if it's an operator, it checks to see if there are two numbers to do stuff with
	    	{
				numJuan = Integer.parseInt(a.pop());
				numTwo = Integer.parseInt(a.pop());

				//It does its thing -------->

				if(s.peek().equals("+"))
				{
					a.push(Double.toString(numJuan + numTwo));
				}
				else if(s.peek().equals("-"))
				{
					a.push(Double.toString(numJuan - numTwo));
				}
				else if(s.peek().equals("*"))
				{
					a.push(Double.toString(numJuan * numTwo));
				}
				else if(s.peek().equals("/"))
				{
					a.push(Double.toString(numJuan / numTwo));
				}

	    	}
	    	else //Because there are no numbers to do anything with
	    	{
	    		s.pop();
	    	}



    	}

    	while(!a.isEmpty()) //Outprints the "A" Stack, which is the number that ends
    	{
			System.out.print(a.pop());
    	}
    	System.out.println("");
    }


}