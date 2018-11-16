import static java.lang.System.*;
import java.util.Scanner;
import java.io.*;

public class Lab07a
{
	public static void main( String args[] )
	{
		Scanner GetNumGrade = new Scanner(System.in);
		
	
		System.out.println("Please enter a Number Grade:: ");
		int UserInput = GetNumGrade.nextInt();
			Grade g = new Grade(UserInput);
		
		System.out.println(UserInput + "is a " + g.getLetterGrade());
	}
}