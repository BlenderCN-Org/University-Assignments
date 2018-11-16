import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class mountainNMBS {


    public static void main(String[] args)throws IOException
    {
      Scanner fileReader = new Scanner (new File("pr92.dat"));
     int count = fileReader.nextInt();
     System.out.println(count);
     fileReader.nextLine();


     for(int i=0; i<count;i++)
     {
     /*
     	String line= fileReader.nextLine();
     	for(int z=0;z<line.length();z++)
     	{
     		moun.add(Integer.parseInt(line.substring(z,z+1)));
     	}
     	System.out.println(moun);
		*/
     	String line= fileReader.nextLine();
     	Scanner chopper = new Scanner(line);
     	ArrayList<Integer> moun = new ArrayList<Integer>();
     	while(chopper.hasNextInt())
     	{
     	moun.add(chopper.nextInt());
     	}
		String output="  MOUNTAIN";
     	boolean falling=false;
		boolean rising= false;
		int a=0;
     	while(a==0)
     	{
			 int u= 0;
     	for(int z=0;z<moun.size()-1;z++)
     	{

     		if(moun.get(z)<moun.get(z+1))
     		{
     			rising=true;

     			if(falling== true)
     			{
     				output= "NOT MOUNTAIN";

     			a++;
     			}
     			System.out.println("rising");

     			}

     		if(moun.get(z+1)<moun.get(z))
     		{
     			System.out.println("falling");
				falling=true;
				u++;
     		}
     	}

     //	System.out.println(u);
     	if (u<1)
     		output="NOT MOUNTAIN";
     	a++;
     	System.out.println(output);
     	}
    }


     }
}







