import java.util.*;
import java.io.*;
import java.lang.*;
import java.lang.System.*;

// Division Teams: PLAY EVERY DIV TEAM, IF MROE GAMES NEEDED, PLAY 1 DIV 2, REST IN ALLI


public class FootballFever {

	private static ArrayList<String> Division1 = new ArrayList<String>();
	private static ArrayList<String> Division2 = new ArrayList<String>();
	private static ArrayList<String> AllianceT = new ArrayList<String>();
	private static ArrayList<String> satTimes = new ArrayList<String>(); //7  
	private static ArrayList<String> wendsTimes = new ArrayList<String>(); //2
	private static ArrayList<String> TimeSlots = new ArrayList<String>();

	private static ArrayList<String> Division1Pairings = new ArrayList<String>();
	private static ArrayList<String> Division2Pairings = new ArrayList<String>();
	private static ArrayList<String> AlliancePairings = new ArrayList<String>();

	private static TeamsLibrary[] Division1arr;
	private static TeamsLibrary[] Division2arr;
	private static TeamsLibrary[] AllianceTarr;

	private static int num;
	private static int satTimesCounter = 0;
	private static int wendsTimesCounter = 0;
	private static int teamNum = 0;
	private static int weeks = 1;   
	private static int Div1Counter = 0;
	private static int Div2Counter = 0;
	private static int AlliCounter = 0;
	private static int check = 0;
	
	private static int EndingCounter1 = 0;
	private static int EndingCounter2 = 0;
	private static int divBetChecker = 0;
	
	private static int Div1uniDiv2 = 0;
	
	private static String Location = "";
	
	private static boolean Div1AllDone = false;
	private static boolean Div2AllDone = false;
	private static boolean Div1OnlyDone = false;
	private static boolean Div2OnlyDone = false;



    public static void main (String [] args)throws IOException
    {
		Scanner fr = new Scanner(new File("Teams.txt"));
		String Changeling = "";
		int Ind = 0;

		satTimes.add("8:00am");
		satTimes.add("10:00am");
		satTimes.add("12:00pm");
		satTimes.add("2:00pm");
		satTimes.add("4:00pm");
		satTimes.add("6:00pm");
		satTimes.add("8:00pm");

		wendsTimes.add("5:30pm");
		wendsTimes.add("7:30pm");
/////////////////////////////////////////////////////////////////////////////////////////// Division 1
		Changeling = fr.nextLine();
		Ind = Changeling.indexOf("(");
		Changeling = Changeling.substring(Ind+1, Changeling.length()-1);
		//System.out.println (Changeling);

		num = Integer.parseInt(Changeling);
		teamNum += num;

		Division1arr = new TeamsLibrary[num];

		String addTeamD1 = "";

		for(int x = 0; x<num; x++) // Fills Division 1
		{
			addTeamD1 = fr.nextLine();
			Division1arr[x]= new TeamsLibrary(addTeamD1,0);			
			Division1.add(addTeamD1);
		    Division1arr[x].addPlayedTeams(Division1.get(x));
		//	System.out.println (Division1.get(x));
		//	System.out.println (Division1arr[x]);
		//	System.out.println ("");

		}

		fr.nextLine();
		//System.out.println ("---------------------------------------");
/////////////////////////////////////////////////////////////////////////////////////////// Division 2
		Changeling = fr.nextLine();
		Ind = Changeling.indexOf("(");
		Changeling = Changeling.substring(Ind+1, Changeling.length()-1);
		//System.out.println (Changeling);

		num = Integer.parseInt(Changeling);
		teamNum += num;
		Division2arr = new TeamsLibrary[num];

		String addTeamD2 = "";

		for(int x = 0; x<num; x++) // Fills Division 2
		{
			addTeamD2 = fr.nextLine();
			Division2arr[x]= new TeamsLibrary(addTeamD2,2);
			Division2.add(addTeamD2);
			Division2arr[x].addPlayedTeams(Division2.get(x));
		//	System.out.println (Division2.get(x));
		//	System.out.println (Division2arr[x]);
		//	System.out.println ("");

		}

		fr.nextLine();
		//System.out.println ("---------------------------------------");
/////////////////////////////////////////////////////////////////////////////////////////// Alliance Teams
		Changeling = fr.nextLine();
		Ind = Changeling.indexOf("(");
		Changeling = Changeling.substring(Ind+1, Changeling.length()-1);
		//System.out.println (Changeling);

		num = Integer.parseInt(Changeling);
		teamNum += num;
		AllianceTarr = new TeamsLibrary[num];

		String addTeamAl = "";

		for(int x = 0; x<num; x++) // Fills Division 2
		{
			addTeamAl = fr.nextLine();
			AllianceTarr[x]= new TeamsLibrary(addTeamAl,3);
			AllianceT.add(addTeamAl);
			AllianceTarr[x].addPlayedTeams(AllianceT.get(x));
			//System.out.println (AllianceT.get(x));
			//System.out.println (AllianceTarr[x]);
			//System.out.println ("");

		}

	//	System.out.println ("---------------------------------------");
	//	System.out.println ("");

		Execute();



/////////////////////////////////////////////////////////////////////////////////////////// Done

    }

    public static void Execute() throws IOException
    {
		for(int x = 0; x < Division1.size(); x++)
		{
			for(int f = 0; f < Division1.size(); f++)
			{
				if(Division1arr[x].TeamsPlayed(Division1arr[f].toString()) == false)
				{
					Division1arr[x].addPlayedTeams(Division1arr[f].toString());
					Division1arr[f].addPlayedTeams(Division1arr[x].toString());
					
					Division1arr[x].addTXT(Division1arr[x].toString() + " vs " + Division1arr[f].toString());
					Division1arr[f].addTXT(Division1arr[f].toString() + " vs " + Division1arr[x].toString());
				}
			}
		}
		
		for(int x = 0; x < Division2.size(); x++)
		{
			for(int f = 0; f < Division2.size(); f++)
			{
				if(Division2arr[x].TeamsPlayed(Division2arr[f].toString()) == false)
				{
					Division2arr[x].addPlayedTeams(Division2arr[f].toString());
					Division2arr[f].addPlayedTeams(Division2arr[x].toString());
					
					Division2arr[x].addTXT(Division2arr[x].toString() + " vs " + Division2arr[f].toString());
					Division2arr[f].addTXT(Division2arr[f].toString() + " vs " + Division2arr[x].toString());
				}
			}
		}
		
		for(int l = Division1.size()-1; l < 10; l++)
		{
			System.out.println ("L: " + l);
			
			if(Div1uniDiv2 == 0)
			{
				for(int m = 0; m < Division1.size(); m++)
				{
					if(Division1arr[m].TeamsPlayed(Division2arr[m].toString()) == false)
					{
						Division1arr[m].addPlayedTeams(Division2arr[m].toString());
						Division2arr[m].addPlayedTeams(Division1arr[m].toString());
						
						Division1arr[m].addTXT(Division1arr[m].toString() + " vs " + Division2arr[m].toString());
						Division2arr[m].addTXT(Division2arr[m].toString() + " vs " + Division1arr[m].toString());
					}
				}
				
				Div1uniDiv2++;
			}
			else // Problem Here ***
			{
				for(int b = 0; b < 10 - Division1.size()-1; b++)
				{
					for(int x = 0; x < Division1.size(); x++)
					{
						for(int f = 0; f < 10 - Division1.size(); f++)
						{
							if(Division1arr[x].TeamsPlayed(AllianceTarr[f].toString()) == false)
							{
								Division1arr[x].addPlayedTeams(AllianceTarr[f].toString());
								AllianceTarr[f].addPlayedTeams(Division1arr[x].toString());
								
								Division1arr[x].addTXT(Division1arr[x].toString() + " vs " + AllianceTarr[f].toString());
								AllianceTarr[f].addTXT(AllianceTarr[f].toString() + " vs " + Division1arr[x].toString());
							}
						}
					}
					
					for(int x = 0; x < Division2.size(); x++)
					{
						for(int f = 0; f < 10 - Division1.size(); f++)
						{
							if(Division2arr[x].TeamsPlayed(AllianceTarr[f].toString()) == false)
							{
								Division2arr[x].addPlayedTeams(AllianceTarr[f].toString());
								AllianceTarr[f].addPlayedTeams(Division2arr[x].toString());
								
								Division2arr[x].addTXT(Division2arr[x].toString() + " vs " + AllianceTarr[f].toString());
								AllianceTarr[f].addTXT(AllianceTarr[f].toString() + " vs " + Division2arr[x].toString());
							}
						}
					}
				}
			}
		}
		
		//OutPrint();
		
		System.out.println ("");
		System.out.println ("");
		System.out.println ("");
		System.out.println ("");
		
		for(int a = 0; a < Division1.size(); a++)
		{
			Division1arr[a].shuffleArray();
		}
		for(int b = 0; b < Division2.size(); b++)
		{
			Division2arr[b].shuffleArray();
		}
		
		OrganizeTimes();
		
		OutPrint();			
	}
	
	public static void OutPrint()
	{
		System.out.println ("-----------------------------------------");
		for(int x =0; x < Division1.size(); x++)
		{				
			Division1arr[x].printTXT();
			System.out.println ("//////////////////////////////////////////");
		}
			
		System.out.println ("-----------------------------------------");			
		
		for(int x =0; x < Division2.size(); x++)
		{				
			Division2arr[x].printTXT();
			System.out.println ("//////////////////////////////////////////");
		}
						
		System.out.println ("-----------------------------------------");	
	}
	
	public static void OrganizeTimes()
	{
		for(int weeksNum = 0; weeksNum < 9; weeksNum++)
		{
			if(weeksNum == 0)
			{
				firstWeek(weeksNum+1);
			}
			else if(weeksNum > 0 && weeksNum < 8)
			{
				middleWeek(weeksNum+1);
			}
			else
			{
				endWeek(weeksNum+1);
			}
		}
	}
	
	public static void firstWeek(int weeksNum)
	{
		for(int x = 0; x < Division1.size()-1; x+=2)
		{
			if(satTimesCounter < satTimes.size())
			{
				if(Division1arr[x].getHomeGames() > Division1arr[x].getAwayGames())
				{
					Location = "AWAY";
					Division1arr[x].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
					Division1arr[x].addAwayGames();
					Location = "HOME";
					Division1arr[x+1].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
					Division1arr[x+1].addHomeGames();
					satTimesCounter++;
				}
				else
				{
					Location = "HOME";
					Division1arr[x].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
					Division1arr[x].addHomeGames();
					Location = "AWAY";
					Division1arr[x+1].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
					Division1arr[x+1].addAwayGames();
					satTimesCounter++;
				}
			}
		}
		
			if(satTimesCounter < satTimes.size())
			{
				if(Division1arr[Division1.size()-1].getHomeGames() > Division1arr[Division1.size()-1].getAwayGames())
				{
					Location = "AWAY";
					Division1arr[Division1.size()-1].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,Division1.size()-1);
					Division1arr[Division1.size()-1].addAwayGames();
					
					satTimesCounter++;
				}
				else
				{
					Location = "HOME";
					Division1arr[Division1.size()-1].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,Division1.size()-1);
					Division1arr[Division1.size()-1].addHomeGames();
					
					satTimesCounter++;
				}
			}
			
		for(int x = 0; x < Division2.size()-1; x+=2)
		{
			if(satTimesCounter < satTimes.size())
			{
				if(Division2arr[x].getHomeGames() > Division2arr[x].getAwayGames())
				{
					Location = "AWAY";
					Division2arr[x].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
					Division2arr[x].addAwayGames();
					Location = "HOME";
					Division2arr[x+1].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
					Division2arr[x+1].addHomeGames();
					satTimesCounter++;
				}
				else
				{
					Location = "HOME";
					Division2arr[x].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
					Division2arr[x].addHomeGames();
					Location = "AWAY";
					Division2arr[x+1].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
					Division2arr[x+1].addAwayGames();
					satTimesCounter++;
				}
			}
		}
		
		satTimesCounter = 0;
		wendsTimesCounter = 0;		
	}
	
	public static void middleWeek(int weeksNum)
	{
		for(int x = 0; x <= Division1.size()-1; x+=2)
		{
			if(wendsTimesCounter < wendsTimes.size() && Division1arr[x].checkWends() == false) 
			{				
				if(x == Division1.size()-1) // ODD GAMES
				{
					if(Division1arr[x].getHomeGames() > Division1arr[x].getAwayGames())
					{
						Location = "AWAY";
						Division1arr[x].editTXT("Game: " + weeksNum + " Wendnesday " + wendsTimes.get(wendsTimesCounter) + " " + Location,x);
						Division1arr[x].addAwayGames();
						Location = "HOME";
					
						wendsTimesCounter++;
						Division1arr[x].addWends();
					
					}
					else
					{
						Location = "HOME";
						Division1arr[x].editTXT("Game: " + weeksNum + " Wendnesday " + wendsTimes.get(wendsTimesCounter) + " " + Location,x);
						Division1arr[x].addAwayGames();
						Location = "AWAY";
						
						wendsTimesCounter++;
						Division1arr[x].addWends();					
					}
				}
				else
				{
					if(Division1arr[x].getHomeGames() > Division1arr[x].getAwayGames())
					{
						Location = "AWAY";
						Division1arr[x].editTXT("Game: " + weeksNum + " Wendnesday " + wendsTimes.get(wendsTimesCounter) + " " + Location,x);
						Division1arr[x].addAwayGames();
						Location = "HOME";
						Division1arr[x+1].editTXT("Game: " + weeksNum + " Wendnesday " + wendsTimes.get(wendsTimesCounter) + " " + Location,x);
						Division1arr[x+1].addHomeGames();
						wendsTimesCounter++;
						Division1arr[x].addWends();
						Division1arr[x+1].addWends();
					}
					else
					{
						Location = "HOME";
						Division1arr[x].editTXT("Game: " + weeksNum + " Wendnesday " + wendsTimes.get(wendsTimesCounter) + " " + Location,x);
						Division1arr[x].addAwayGames();
						Location = "AWAY";
						Division1arr[x+1].editTXT("Game: " + weeksNum + " Wendnesday " + wendsTimes.get(wendsTimesCounter) + " " + Location,x);
						Division1arr[x+1].addAwayGames();
						wendsTimesCounter++;
						Division1arr[x].addWends();
						Division1arr[x+1].addWends();
					}
				}				 	
			}
			
			else if(satTimesCounter < satTimes.size())
			{
				if(x == Division1.size()-1) // ODD GAMES
				{
					if(Division1arr[x].getHomeGames() > Division1arr[x].getAwayGames())
					{
						Location = "AWAY";
						Division1arr[x].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
						Division1arr[x].addAwayGames();
						
						satTimesCounter++;
					}
					else
					{
						Location = "HOME";
						Division1arr[x].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
						Division1arr[x].addHomeGames();						
						
						satTimesCounter++;
					}
				}
				else
				{
					if(Division1arr[x].getHomeGames() > Division1arr[x].getAwayGames())
					{
						Location = "AWAY";
						Division1arr[x].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
						Division1arr[x].addAwayGames();
						Location = "HOME";
						Division1arr[x+1].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
						Division1arr[x+1].addHomeGames();
						satTimesCounter++;
					}
					else
					{
						Location = "HOME";
						Division1arr[x].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
						Division1arr[x].addHomeGames();
						Location = "AWAY";
						Division1arr[x+1].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
						Division1arr[x+1].addAwayGames();
						satTimesCounter++;
					}
				}
			}
		}
		
		for(int x = 0; x < Division2.size()-1; x+=2)
		{
			if(wendsTimesCounter < wendsTimes.size()) 
			{
				if(Division2arr[x].checkWends() == false && Division2arr[x+1].checkWends() == false)
				{
					if(Division2arr[x].getHomeGames() > Division2arr[x].getAwayGames())
					{
						Location = "AWAY";
						Division2arr[x].editTXT("Game: " + weeksNum + " Wendnesday " + wendsTimes.get(wendsTimesCounter) + " " + Location,x);
						Division2arr[x].addAwayGames();
						Location = "HOME";
						Division2arr[x+1].editTXT("Game: " + weeksNum + " Wendnesday " + wendsTimes.get(wendsTimesCounter) + " " + Location,x);
						Division2arr[x+1].addHomeGames();
						wendsTimesCounter++;
						Division2arr[x].addWends();
						Division2arr[x+1].addWends();
					}
					else
					{
						Location = "HOME";
						Division2arr[x].editTXT("Game: " + weeksNum + " Wendnesday " + wendsTimes.get(wendsTimesCounter) + " " + Location,x);
						Division2arr[x].addAwayGames();
						Location = "AWAY";
						Division2arr[x+1].editTXT("Game: " + weeksNum + " Wendnesday " + wendsTimes.get(wendsTimesCounter) + " " + Location,x);
						Division2arr[x+1].addAwayGames();
						wendsTimesCounter++;
						Division2arr[x].addWends();
						Division2arr[x+1].addWends();
					}
				} 	
			}
			
			else if(satTimesCounter < satTimes.size())
			{
				if(Division2arr[x].getHomeGames() > Division2arr[x].getAwayGames())
				{
					Location = "AWAY";
					Division2arr[x].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
					Division2arr[x].addAwayGames();
					Location = "HOME";
					Division2arr[x+1].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
					Division2arr[x+1].addHomeGames();
					satTimesCounter++;
				}
				else
				{
					Location = "HOME";
					Division2arr[x].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
					Division2arr[x].addHomeGames();
					Location = "AWAY";
					Division2arr[x+1].editTXT("Game: " + weeksNum + " Saturday " + satTimes.get(satTimesCounter) + " " + Location,x);
					Division2arr[x+1].addAwayGames();
					satTimesCounter++;
				}
			}
		}
		satTimesCounter = 0;
		wendsTimesCounter = 0;		
	}
	
	public static void endWeek(int weeksNum)
	{
		
	}
	
}    
		
	





