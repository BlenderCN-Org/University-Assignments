import java.util.*;
import static java.lang.System.*;

public class Grade 
{
	private int numGrade;

	public Grade()
	{
	numGrade = 0;

	}
/////////////////////////////////////////////////////////////////////////
	public Grade(int grade)
	{
	
	numGrade = grade;

	}
/////////////////////////////////////////////////////////////////////////
	public void setGrade(int grade)
	{
		Scanner GradeSetter = new Scanner(System.in);
		System.out.println("Please enter the grade that you would like to set:: ");
		int Input = GradeSetter.nextInt();
		grade = Input;
		System.out.println("Your Grade has been set!");
	}
/////////////////////////////////////////////////////////////////////////
	public String getLetterGrade( )
	{
		String letGrade="";
		
		if(numGrade >= 90)
		{
			letGrade = "A";	
		}
		
		else if(numGrade >= 80 && numGrade < 90)
		{
			letGrade = "B";
		}
		
		else if(numGrade >= 70 && numGrade < 80)
		{
			letGrade = "C";
		}
		
		else
		{
			letGrade = "D";
		}


		return letGrade;
	}
/////////////////////////////////////////////////////////////////////////
	public String toString()
	{
		return numGrade + " is a " + getLetterGrade() + "\n";
	}
/////////////////////////////////////////////////////////////////////////
}