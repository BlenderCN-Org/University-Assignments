import java.util.*;
import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;

//Unregistered Teams + Times
//Has to play at least 1 Unregistered Team
//Must use times 8:00am 10:00am 12:00pm 2:00pm 4:00pm 6:00pm 8:00pm

public class BracketGenerator 
{	
	private static Scanner kb = new Scanner (System.in);
	private static ArrayList<String> Bracket = new ArrayList<String>();
	private static int RoundNum = 1;
	private static int NumPlayers;
	private static int GameNum = 1;
	private static int PlayerNum;

    public static void main (String [] args)
    {
		BracketGen();
    }
    
    public static void BracketGen()
    {    	
    	System.out.println("Welcome to Matthew's Bracket Gen! How many players will be participating?"); 
    	NumPlayers = kb.nextInt();    	
    
    	System.out.println("-------------------------------------------------------------------------------");    
    	
    	NamePlayers(NumPlayers);  	
    	
    }
    
    public static void NamePlayers(int NumPlayers)
    {   	
    	int Counter = 1;
    	
    	for(int i = 0; i < NumPlayers; i ++)
    	{
    		System.out.println ("Please Input Player " + Counter + "'s Name:: ");
    		String Name = kb.next();
    		Bracket.add(Name);
    		Counter++;
    		System.out.println("");
    	}
    	
    	System.out.println("-------------------------------------------------------------------------------");
    	GenerateBracket();
    }
    
   	public static void GenerateBracket()
   	{ 		
   		if(NumPlayers%2 == 0)
   		{ 		
   			EvenNumBracket();
   		}
   		else
   		{
   			OddNumBracket();
   		}
   	}
   	
   	public static void EvenNumBracket()
   	{   		
   		int ProxyNumPlayers = NumPlayers;
   		int modNumPlayers = NumPlayers/2;
   			 
   		Random r = new Random();  
   			
   		if(NumPlayers != 1)
   		{
	   		for(int x = 0; x < modNumPlayers; x++)
	   		{
	   			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	   			System.out.println("Pairings for Round " + RoundNum + " | Game " + GameNum);
	   			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		   		PlayerNum = r.nextInt(ProxyNumPlayers-0)+0;	
		   		System.out.println("Player:: " + Bracket.get(PlayerNum));
		   		Bracket.remove(PlayerNum);
		   		ProxyNumPlayers--;
		   		
		   		System.out.println("VS");
		   		
		   		PlayerNum = r.nextInt(ProxyNumPlayers-0)+0;	
		   		System.out.println("Player:: " + Bracket.get(PlayerNum));
		   		Bracket.remove(PlayerNum);
		   		ProxyNumPlayers--;	   			
		   		
		   		GameNum++;
		   		
		   		System.out.println("");
	   		}
	   		
	   		GameNum = 1;
	   		
	 		for(int p = 0; p < modNumPlayers; p++)
	 		{
	 			System.out.println("Please Enter the Name of the Winner of Game " + GameNum);
	 			String Name = kb.next();
	 			Bracket.add(Name);
	 			GameNum++; 			 			
	 		}
	 		
	 		NumPlayers = Bracket.size();
	 		GameNum = 1;
	 		RoundNum++;
	 		System.out.println("-------------------------------------------------------------------------------"); 		
	 		EvenNumBracket(); 		
   		}
   		else
   		{   			
   			System.out.println("-------------------------------------------------------------------------------");
   			System.out.println("-------------------------------------------------------------------------------");
   			System.out.println("-------------------------------------------------------------------------------");
   			System.out.println("-------------------------------------------------------------------------------");
   			System.out.println ("The Winner is " + Bracket.get(0) + "!");
   			System.out.println("-------------------------------------------------------------------------------");
   			System.out.println("-------------------------------------------------------------------------------");
   			System.out.println("-------------------------------------------------------------------------------");
   			System.out.println("-------------------------------------------------------------------------------");
   			
   		}	
   	}
   	
   	public static void OddNumBracket()
   	{
   		int ProxyNumPlayers = NumPlayers;
   		int modNumPlayers = (NumPlayers/2)+1;
   			 
   		Random r = new Random();  
   			
   		if(Bracket.size() == 2)
	   	{
	   		modNumPlayers--;	
	   	}
   			
   		if(NumPlayers != 1)
   		{
	   		for(int x = 0; x < modNumPlayers; x++)
	   		{   			
	   			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	   			System.out.println("Pairings for Round " + RoundNum + " | Game " + GameNum);
	   			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		   		PlayerNum = r.nextInt(ProxyNumPlayers-0)+0;	
		   		System.out.println("Player:: " + Bracket.get(PlayerNum));
		   		Bracket.remove(PlayerNum);
		   		ProxyNumPlayers--;
		   		
		   		System.out.println("VS");
		   		
		   		if(Bracket.size() != 0 )
		   		{
		   			PlayerNum = r.nextInt(ProxyNumPlayers-0)+0;	
			   		System.out.println("Player:: " + Bracket.get(PlayerNum));
			   		Bracket.remove(PlayerNum);
			   		ProxyNumPlayers--;	   			
			   		
			   		GameNum++;
			   		
			   		System.out.println("");
		   		}
		   		else
		   		{
		   			System.out.println("Player:: BYE");
		   			System.out.println("");
		   		}
		   		
	   		}
	   		
	   		GameNum = 1;
	   		
	 		for(int p = 0; p < modNumPlayers; p++)
	 		{
	 			System.out.println("Please Enter the Name of the Winner of Game " + GameNum);
	 			String Name = kb.next();
	 			Bracket.add(Name);
	 			GameNum++; 			 			
	 		}
	 		
	 		NumPlayers = Bracket.size();
	 		GameNum = 1;
	 		RoundNum++;
	 		System.out.println("-------------------------------------------------------------------------------"); 		
	 		OddNumBracket(); 		
   		}
   		else
   		{   			
   			System.out.println("-------------------------------------------------------------------------------");
   			System.out.println("-------------------------------------------------------------------------------");
   			System.out.println("-------------------------------------------------------------------------------");
   			System.out.println("-------------------------------------------------------------------------------");
   			System.out.println ("The Winner is " + Bracket.get(0) + "!");
   			System.out.println("-------------------------------------------------------------------------------");
   			System.out.println("-------------------------------------------------------------------------------");
   			System.out.println("-------------------------------------------------------------------------------");
   			System.out.println("-------------------------------------------------------------------------------");
   			
   		}	
   		
   	}
    
 }
