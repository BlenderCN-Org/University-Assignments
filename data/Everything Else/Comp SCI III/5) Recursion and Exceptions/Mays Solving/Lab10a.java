import java.util.*;
import java.io.*;
import java.lang.*;

public class Lab10a
{
	public static void main( String[] args ) throws IOException
	{
		Scanner kb = new Scanner(System.in);
		
		String[] Lis;
		
		String Line = "CA XY RS YS ST TB AX BD RJ";
		int index = 0;
		boolean Checker = false;
		
		System.out.println("What is the First Char?");
		String AnswerJuan = kb.next();
		
		System.out.println("What is the Second Char?");
		String AnswerDos = kb.next();
		
	//**For Directly Connected**   Lis = Line.split(" ");
	
		index = Line.indexOf(AnswerJuan);		
	
		for(int x = index; x < Line.length()-1; x++)
		{			
			if(Character.toString(Line.charAt(x+1)).equals(AnswerDos))
			{
				x = Line.length();
				Checker = true;				
			}
		}
		
		System.out.println(Checker);
	
		
		
		
	}
}