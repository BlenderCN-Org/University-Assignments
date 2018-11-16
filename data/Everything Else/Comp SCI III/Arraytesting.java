/**
 * @(#)Arraytesting.java
 *
 *
 * @author
 * @version 1.00 2015/9/2
 */

public class Arraytesting {


    public static void main(String[] args) {
       int[] num = new int[20];
       int sum=0;
      int high=0;
       int low=20;


       for (int i=0;i<20;i++)
       {
       	num[i] = (int)(Math.random()*(20))+1;
       	}

       for (int i=0;i<20;i++)
       {System.out.println(num[i]);
       		if (num[i]>high)
       		high=num[i];
       		if (num[i]<low)
       		low=num[i];
       		sum+=num[i];

       }




       for (int z=0;z<20;z++)
      {
       for(int i=0;i<19;i++)
       {
       	int x=num[i];
       	int y=num[i+1];
       	if(num[i]>num[i+1])
       	{	num[i]=y;
       		num[i+1]=x;
       	}
       }

       }

       System.out.println("high= "+high+"\nLow = "+low+" \nAvg = "+sum/20);
		System.out.print("sorted list:");

		for (int i=0;i<20;i++)
		{
			System.out.print(num[i]+",");}
			System.out.println("\n");




   for(int i=1;i<=20;i++)
   {
   	 System.out.print(i);
   	for(int x=0;x<20;x++)
   	{

   	  if(i==num[x])
   	  	System.out.print("*");
   	}
	System.out.println("");
   }
    }

       }




