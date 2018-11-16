import java.util.*;
import java.io.*;
import java.lang.*;
import java.lang.System.*;

public class TheFinalHurahh { 
	
	private static ArrayList<String> satTimes = new ArrayList<String>(); //7  
	private static ArrayList<String> wendsTimes = new ArrayList<String>(); //2
	
	private static TeamsLibrary[] Division1arr;
	private static TeamsLibrary[] Division2arr;
	private static TeamsLibrary[] AllianceTarr;

	private static int num;
	private static int satTimesCounter = 0;
	private static int wendsTimesCounter = 0;
	private static int teamNum = 0;	
	private static int wendsPlayed = 0;

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
		

		num = Integer.parseInt(Changeling);
		

		Division1arr = new TeamsLibrary[num];

		String addTeamD1 = "";

		for(int x = 0; x<num; x++) // Fills Division 1
		{
			addTeamD1 = fr.nextLine();
			Division1arr[x]= new TeamsLibrary(addTeamD1);
			Division1arr[x].addTeamsPlayed(Division1arr[x]);		
		}

		fr.nextLine();
		
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
			Division2arr[x]= new TeamsLibrary(addTeamD2);
			Division2arr[x].addTeamsPlayed(Division2arr[x]);			
			
		}

		fr.nextLine();
		
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
			AllianceTarr[x]= new TeamsLibrary(addTeamAl);
			AllianceTarr[x].addTeamsPlayed(AllianceTarr[x]);		
			
		}
		
		WeeksOne();
	}
		
	public static void WeeksOne()
	{
		
	}
	public static void WeeksMore()
	{
		
	}
	public static void WeeksEnd()
	{
		
	}
	
	public static void Saturday()
	{
		
	}
	public static void Wednesday()
	{
		
	}
    
}