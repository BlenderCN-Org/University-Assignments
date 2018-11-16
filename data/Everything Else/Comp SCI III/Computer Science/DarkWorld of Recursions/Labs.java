import java.util.*;

public class Labs {

    public static void main (String [] args)
    {

		Lab03();
    }

    public static int Lab01(int n)
    {
    	if(n>0)
    	{
    		return n + Lab01(n-1);
    	}
    	else
    	{
    		return 0;
    	}
    }

     public static int Lab02(int n)
    {
    	if(n>1)
    	{
    		return n*Lab02(n-1);
    	}
    	else
    	{
    		return 1;
    	}
    }

     public static int Lab03()
    {
    	Scanner kb = new Scanner (System.in);

    	System.out.println("You have chosen (Lab03: Euclid's Algorithm for finding GDC'S)!");
    	System.out.println("Please enter the first number:: ");
    	int num1 = kb.nextInt();
    	System.out.println("Please enter the second number:: ");
    	int num2 = kb.nextInt();

    	int GCF1 = 0;
    	int GCF2 = 0;
    	int Checker = 0;

    	int remainder = 0;

    	if(num1==0)
    	{
    		System.out.println("The GDC of " + num1 + "," + num2 + " is " + num2 + " because since the of " + num1 + "," + num2 + " is " + num2 + " we can stop. ");
    	}
    	else if(num2==0)
    	{
			System.out.println("The GDC of " + num1 + "," + num2 + " is " + num1 + " because since the of " + num1 + "," + num2 + " is " + num1 + " we can stop. ");
    	}
    	else
    	{
    		 if (num1 >= num2)
                {
                    num1 = num1 - num2;
                    Checker = num1;
                    System.out.println(Checker);

                }
                else if (num2 >= num1)
                {
                    num2 = num2 - num1;
                    Checker = num2;
                    System.out.println(Checker);
                }
                else
                {
                	System.out.println("BBBBBBBBBBBAAAAAAAAAAAAAAAAAAAAAAKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                }
       }

       return Checker;

    }

      public static void Lab04()
    {
    	Scanner kb = new Scanner (System.in);

    	System.out.println("You have chosen (Lab02: Recursive Factorial)!");
    	System.out.println("Please enter a number:: ");

    }


}