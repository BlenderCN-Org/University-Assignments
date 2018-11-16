import java.util.*;


public class CleverBot {

    public static void main(String [] args)
    {


		ResponseStart();

    }

    public static void ResponseStart()
    {

   		Scanner kb = new Scanner(System.in);

		System.out.println("");
		String UI01 = kb.next();

		CheckHi(UI01);


    }
	//Notes, Longest Terms must be First, due to checking priotity
	//Change no the "Else" Statement on each other Method to link to the other Method
    public static void CheckHi(String Resp)
    {
    	String WhatIsSaid = Resp;
    	String Checker = "Hi";

    	boolean DoesItPass = false;
    	int x = 0;

    	for(int i=0; i<WhatIsSaid.length(); i++)
    	{
    		if(x == Checker.length()-1)
    		{
    		 DoesItPass = true;
    		}

    		if(WhatIsSaid.charAt(i) == Checker.charAt(x))
    		{
    			x++;
    		}
    		else
    		{
    			x=0;
    		}
    	}

		if(DoesItPass == true)
		{
			System.out.println("Salutations!");
		}
		else
		{
			System.out.println("I dont speak spanish");
		}
    }

    public static void CheckHey(String Resp)
    {
    	String WhatIsSaid = Resp;
    	String Checker = "Hey";

    	boolean DoesItPass = false;
    	int x = 0;

    	for(int i=0; i<WhatIsSaid.length(); i++)
    	{
    		if(x == Checker.length()-1)
    		{
    		 DoesItPass = true;
    		}

    		if(WhatIsSaid.charAt(i) == Checker.charAt(x))
    		{
    			x++;
    		}
    		else
    		{
    			x=0;
    		}
    	}

		if(DoesItPass == true)
		{
			System.out.println("Yo");
		}
		else
		{
			System.out.println("I dont speak spanish");
		}

  }


public static void CheckWhats Up(String Resp)
    {
    	String WhatIsSaid = Resp;
    	String Checker = "Whats Up";

    	boolean DoesItPass = false;
    	int x = 0;

    	for(int i=0; i<WhatIsSaid.length(); i++)
    	{
    		if(x == Checker.length()-1)
    		{
    		 DoesItPass = true;
    		}

    		if(WhatIsSaid.charAt(i) == Checker.charAt(x))
    		{
    			x++;
    		}
    		else
    		{
    			x=0;
    		}
    	}

		if(DoesItPass == true)
		{
			System.out.println("Not much, how are you?");
		}
		else
		{
			System.out.println("I dont speak spanish");
		}
    }


