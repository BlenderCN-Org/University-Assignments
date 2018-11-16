//© A+ Computer Science
// www.apluscompsci.com

//exception example 4

import static java.lang.System.*;

public class ExceptionFour
{
	public static void main(String args[])
	{
		Scanner kb = new Scanner(in);
		out.print("Enter an integer :: ");
		int num=kb.nextInt();
		int answer=0;

		try{
			if(num=0)
		   		throw new Exception("num==0");
		   	else
		   	   answer=90/num;
		}

		//must have a catch of finally with a try
		out.println("the answer is "+answer);
	}
}