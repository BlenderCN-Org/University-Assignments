//© A+ Computer Science
// www.apluscompsci.com

//exception example 6

import static java.lang.System.*;
import java.util.Scanner;

public class ExceptionSix
{
	public static void main(String args[])
	{
		Scanner kb = new Scanner(in);
		out.print("Enter an integer :: ");
		int num=kb.nextInt();
		int answer=0;

		try{
			if(num==0)
		   		throw new Exception("num==0");
		   	else
		   	   answer=32/num;

		}
		catch(NullPointerException e){
		   out.println(e + "Exception");
		}
		catch(ClassCastException e){
		   out.println(e + "Exception");
		}
		catch(Exception e){
		   out.println(e + "Exception");
		}
		finally{
		   out.println("this always happens");
		}
		out.println("the answer is "+answer);
	}
}
