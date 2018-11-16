import java.util.*;
import java.io.*;

public class MainGameEngine impliments Comparable
{

    public static void main (String [] args)
    {

    }


	public static void MainMenu ()
	{
		Scanner uInput = new Scanner(System.in);
		
		System.out.println("*****************************************************");
		System.out.println("*********Welcome to Fighting Simulator 2016!*********");
		System.out.println("*****************************************************");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("|1: Singleplayer Game |");
		System.out.println("|2: Multiplayer Game |");
		System.out.println("|3: Credits |");
		System.out.println("|4: Help |");
		int MainMenuChoice = uInput.nextInt();
		
		if(MainMenuChoice == 1)
		{
			SingleplayerGame();
		}
		else if(MainMenuChoice == 2)
		{
			TeamOneNameSelect();
		}
		else if(MainMenuChoice == 3)
		{
			Credits();
		}
		else if(MainMenuChoice == 4)
		{
			Help();
		}
		else
		{
			MainMenu();
			System.out.println("That was an incorrect command, please type a correct command.")
		}
		
		
	}
	
	public static void SingleplayerGame()
	{
		
	}
	
	public static void TeamOneNameSelect() // May need to return team value of some sort.
	{
		Scanner uInput = new Scanner(System.in);
		int TeamNameCounter1 = 0;		
		
		System.out.println("");
		System.out.println("");
		System.out.println("Player 1, please choose a team name.");
		String Teamname1 = uInput.next();
		
		if(TeamNameCounter1 == 0)
		{		
			System.out.println("Player 1, your team name is |" + Teamname1 + "|, is this correct?");
			System.out.println("|1: Yes |");
			System.out.println("|2: No |");
			int TeamNameCorrect1 = uInput.next();
			
			if(TeamNameCorrect1 == 1)
			{
				TeamNameCounter1++;
				TeamTwoNameSelect();
			}
			else(TeamNameCorrect1 == 2)
			{
				TeamOneNameSelect();
			}
				
		}		
	}
	
	public static void TeamTwoNameSelect() // May need to return team value of some sort.
	{
		Scanner uInput = new Scanner(System.in);
		int TeamNameCounter2 = 0;
		
		System.out.println("");
		System.out.println("");
		System.out.println("Player 2, please choose a team name.");
		String Teamname2 = uInput.next();
		
		if(TeamNameCounter2 == 0)
		{		
			System.out.println("Player 2, your team name is |" + Teamname1 + "|, is this correct?");
			System.out.println("|1: Yes |");
			System.out.println("|2: No |");
			int TeamNameCorrect2 = uInput.next();
			
			if(TeamNameCorrect2 == 1)
			{
				TeamNameCounter2++;
				TeamOneUnitSelect();
			}
			else(TeamNameCorrect2 == 2)
			{
				TeamTwoNameSelect();
			}
				
		}		
		
	}
	
	public static void TeamOneUnitSelect();
	{
		Scanner uInput = new Scanner(System.in);
		int TeamUnitCounter1 = 0;
		
		System.out.println("");
		System.out.println("");
		System.out.println("Player 1, please choose your units. You can have a MAX unit cost of " + 7);
		System.out.println("(1) |Swordsman| Unit Cost: 1");
		System.out.println("(2) |Spearman| Unit Cost: 2");
		System.out.println("(3) |Duelist| Unit Cost: 3");
		System.out.println("(4) |Beserker| Unit Cost: 4");
		System.out.println("(5) |Palidan| Unit Cost: 5");
		System.out.println("Your current unit cost is " + TeamUnitCounter1);
		int aaa = uInput.nextInt();

		//Include loop for team comp ++ will add later

		if(aaa == 1)
		{
		
		int l = Swordsman.getValue();
		TeamUnitCounter1 = TeamUnitCounter1 + l;
		
		}


		if(aaa == 2)
		{
		
		int l = Spearman.getValue();
		TeamUnitCounter1 = TeamUnitCounter1 + l;
		
		}


		if(aaa == 3)
		{
		
		int l = Duelist.getValue();
		TeamUnitCounter1 = TeamUnitCounter1 + l;
		
		}


		if(aaa == 4)
		{
		
		int l = Beserker.getValue();
		TeamUnitCounter1 = TeamUnitCounter1 + l;
		
		}


		if(aaa == 5)	
		{
		
		int l = Palidan.getValue();
		TeamUnitCounter1 = TeamUnitCounter1 + l;
		
		}
		
				
	}
	
	public static void Credits()
	{
		
	}
	
	public static void Help()
	{
		
	}

}