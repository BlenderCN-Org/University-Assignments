import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;



public class HangMySelf {

    public static void main (String [] args) throws IOException
    {
    int FailCount = 0;
	String OPrint = "";
	String Hint = "";
	String Final = "";
	String Jank1 = "";
	String Jank2 = "";
	int Impromptu = 0;
	Scanner kb = new Scanner(System.in);
	Scanner Alphabet = new Scanner( new File( "Alphabet.txt"));
	Scanner fileReader = new Scanner( new File( "CSisBlackMagicCause.txt"));
	ArrayList<Puzzle> part1  = new ArrayList<Puzzle>(); //hint
	ArrayList<String> alpha = new ArrayList<String>();
	ArrayList<Puzzle> part2 = new ArrayList<Puzzle>(); //letter


 		while(Alphabet.hasNext())
 		{
 	 		alpha.add(Alphabet.next());
 		}


 		while(fileReader.hasNext())
 		{
 	 		part1.add ( new Puzzle( fileReader.nextLine(), fileReader.nextLine()) );

 		}

 		for (int j=0; j < part1.size(); j++)
 		{

 		///////////////////////////////////////////////////////////
 		System.out.println("");
 		System.out.println("So far, you have the current phrase " + Final);
 		System.out.println("");
 		System.out.println("This is the alphabet you currently have: ");
 		System.out.println(alpha);
 		///////////////////////////////////////////////////////////
 		System.out.println("");
 		System.out.println(Hint);
 		System.out.println("Please Guess a Letter (All Caps Please)");
 		System.out.println("");
 		String User = kb.next();
 		String a = User;

 		if(User.equals(OPrint))
 		{

 		Final = Final + OPrint;
 		System.out.println("");
 		System.out.println("");
 		System.out.println("--------------------------------------------------------------------------------");
 		///////////////////////////////////////////////////////////
 			for(int i=0; i<alpha.size(); i++ )
 			{
 				if(alpha.get(i).equals(a) )
 				{
 					alpha.remove(i);
 				}
 			}

 		}
 		else
 		{
 		System.out.println("");
 		System.out.println("You guessed wrong.");
 		FailCount = FailCount+1;
 		j--;
 		}
 		if(FailCount == 0)
 		{
 	System.out.println("	-----------");
 	System.out.println("	|		  -");
 	System.out.println("	 		  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");

 		}

 		if(FailCount == 1)
 		{
 	System.out.println("	-----------");
 	System.out.println("	|		  -");
 	System.out.println("	O 		  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");

 		}

 		if(FailCount == 2)
 		{

 	System.out.println("	-----------");
 	System.out.println("	|		  -");
 	System.out.println("	O 		  -");
 	System.out.println("	|		  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");

 		}

 		if(FailCount == 3)
 		{

 	System.out.println("	-----------");
 	System.out.println("	|		  -");
 	System.out.println("	O		  -");
 	System.out.println("       -|		  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");

 		}

 		if(FailCount == 4)
 		{

 	System.out.println("	-----------");
 	System.out.println("	|		  -");
 	System.out.println("	O 		  -");
 	System.out.println("       -|-		  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");

 		}

 		if(FailCount == 5)
 		{

 	System.out.println("	-----------");
 	System.out.println("	|		  -");
 	System.out.println("	O 		  -");
 	System.out.println("       -|-		  -");
 	System.out.println("         l		  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");

 		}

 		if(FailCount == 6)
 		{

 	System.out.println("	-----------");
 	System.out.println("	|		  -");
 	System.out.println("	O 		  -");
 	System.out.println("       -|-		  -");
 	System.out.println("       l l		  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");

	int x = 0;
 	while(x<100)
 		{
 			System.out.println("YOU LOSE!!!");
 			x++;
 		}

 	j = 100;

 		}

 		if(j==100)
 		{
 		System.out.println("");
 		System.out.println("Do you wish to play again? Y/N ");
 		String Loser = kb.next();
 		if(Loser.equals("Y"))
 		{
 			System.out.println("");
 			System.out.println("Please restart the program");
 		}

 		if(Loser.equals("N"))
 		{
 			System.out.println("");
 			System.out.println("Please exit the program");
 		}


 		}





 		///////////////////////////////////////////////////////////

 		if(Final.equals("COMPUTER"))
 		{

 		int x = 0;
 		while(x<100)
 		{
 			System.out.println("YOU WIN!!!");
 			x++;
 		}


 		}

 		}





 	}

 	///////////////////////////////////////////////////////////


 	public static void stickman (String [] args)
 	{

 	System.out.println("	-----------");
 	System.out.println("	|		  -");
 	System.out.println("	 		  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");

 	///////////////////////////////////////////////////////////

 	System.out.println("	-----------");
 	System.out.println("	|		  -");
 	System.out.println("	O 		  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");

 	///////////////////////////////////////////////////////////

 	System.out.println("	-----------");
 	System.out.println("	|		  -");
 	System.out.println("	O 		  -");
 	System.out.println("	|		  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");

 	///////////////////////////////////////////////////////////

 	System.out.println("	-----------");
 	System.out.println("	|		  -");
 	System.out.println("	O		  -");
 	System.out.println("   -|		  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");

 	///////////////////////////////////////////////////////////

 	System.out.println("	-----------");
 	System.out.println("	|		  -");
 	System.out.println("	O 		  -");
 	System.out.println("   -|-		  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");

 	///////////////////////////////////////////////////////////

 	System.out.println("	-----------");
 	System.out.println("	|		  -");
 	System.out.println("	O 		  -");
 	System.out.println("   -|-		  -");
 	System.out.println("     l		  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");

 	///////////////////////////////////////////////////////////

 	System.out.println("	-----------");
 	System.out.println("	|		  -");
 	System.out.println("	O 		  -");
 	System.out.println("   -|-		  -");
 	System.out.println("   l l		  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");
 	System.out.println("			  -");

 	///////////////////////////////////////////////////////////


 	}





}
