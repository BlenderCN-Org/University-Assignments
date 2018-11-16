import java.util.*;
import java.io.*;
import java.lang.*;
import java.lang.System.*;

public class Conncetorss
{
	private static TreeMap<String,ArrayList> map = new TreeMap<String,ArrayList>();
	private static List<String> keys;
	private static int keyPos = 0;
	private static int ArraySize = 0;
	private static boolean isIt = false;

    public static void main (String [] args)throws IOException
    {
    	Map<String,ArrayList>map;
		map = new TreeMap<String,ArrayList>();

		Scanner fn = new Scanner(new File("data.dat"));

		String temp = "";

		while(fn.hasNextLine())
		{
			temp = fn.next();
			if(map.get(temp.substring(0,1)) == null)
			{
				map.put(temp.substring(0,1),new ArrayList<String>(Arrays.asList(temp.substring(1))));
			}
			else
			{
				map.get(temp.substring(0,1)).add(temp.substring(1));
			}
		}

		keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);

		for(String key: keys)
		{
			System.out.println (key + ": " + map.get(key));
		}


		System.out.println ("");

		System.out.println(RecursiveCheck(0,0));
    }

    public static boolean RecursiveCheck(int keyNum, int arrNum)
    {
    	if(map.get(keys.get(keyNum)).get(arrNum).equals("G"))
    	{
    		isIt = true;
    		return isIt;
    	}
    	else
    	{

    	}
		return isIt;
    }
}