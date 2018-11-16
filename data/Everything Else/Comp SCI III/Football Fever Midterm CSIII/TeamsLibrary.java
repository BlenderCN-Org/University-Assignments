import java.util.*;
import java.io.*;
import java.lang.*;
import java.lang.System.*;

public class TeamsLibrary
{
	ArrayList<Object> TeamsPlayed = new ArrayList<Object>();
	ArrayList<String> Bracket
	
	boolean WendsChecker = false;
	
	String TeamName = "";
	
	int HomeGames = 0;
	int AwayGames = 0;
	int gamesPlayed = 0;
	
    public TeamsLibrary(String n)
    {    	
    	TeamName = n;
    }
    
    public boolean CheckIfPlayed(String n)
    {
    	boolean x = false;
    	
    	for(int i = 0; i < TeamsPlayed.size(); i++)
    	{
    		if(TeamsPlayed.get(i).toString().equals(n))
    		{
    			x = true;
    		}
    	}
    	
    	return x;
    }
    
    public void addTeamsPlayed(Object obj)
    {
    	TeamsPlayed.add(obj);
    }
    
    public int getHomeGames()
    {
    	return HomeGames;
    }
    
    public void addHomeGames()
    {
    	HomeGames++;
    }
    
    public int getAwayGames()
    {
    	return AwayGames;
    }
    
    public void addAwayGames()
    {
    	AwayGames++;
    }
    
    public boolean Wends()
    {
    	return WendsChecker;
    }
    
    public void changeWends()
    {
    	WendsChecker = true;
    }
    
    public String toString()
    {
    	return "" + TeamName;
    }
   
    
}
    