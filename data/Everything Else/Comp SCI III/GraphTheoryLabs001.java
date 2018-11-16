import java.util.*;
import java.io.*;
import java.lang.*;


public class GraphTheoryLabs001 {
	
	private static boolean path = false;

    public static void main(String [] args) throws IOException
    {
    	Scanner fr = new Scanner (new File("Dataset.dat"));
    	
    	TreeMap<String, String> aMap = new TreeMap<String, String>();
    	ArrayList<String> key = new ArrayList<String>();
    	ArrayList<String> keyTemp = new ArrayList<String>();    	
    	
    	while(fr.hasNext())
    	{
    		keyTemp.add(fr.next());
    	}
    	
    	System.out.println (keyTemp);
    	
    	for(int i = 0; i < keyTemp.size(); i++)	
    	{
    		if(!(key.contains(keyTemp.get(i).substring(0,1)))) 
    		{
    			key.add(keyTemp.get(i).substring(0,1));
    			keyTemp.get(i).equals(keyTemp.get(i).substring(1));
    		} 
    	}
    	
    	System.out.println (key);
    	
    	for(int i = 0; i < key.size(); i++)
    	{
    		aMap.put(key.get(i),"");
    	}
    	
    	System.out.println ("Map KeySet:: " + aMap.keySet());    		
    	
    		
    		
    }
    
    public boolean rSolve(String f)
    {
    	if(f.equals("TEMP"))
    	{
    		path = true;
    	}
    	else
    	{
    		
    	}
    	
    	return path;
    }
    
    
    
}