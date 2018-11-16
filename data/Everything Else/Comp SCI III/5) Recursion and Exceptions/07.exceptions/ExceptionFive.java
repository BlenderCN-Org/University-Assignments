//© A+ Computer Science
// www.apluscompsci.com

//exception example 5

import static java.lang.System.*;
import java.util.Scanner;

public class ExceptionFive
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
		   	   answer=90/num;

		}
		catch(Exception e){
		   out.println(e);
		}
		out.println("the answer is "+answer);
	}
}