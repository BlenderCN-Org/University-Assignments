import java.util.*;

public class SortingCause
{

    public static void main(String [] args)
    {
    	int[] Array = {6,2,0,8,5}; // |0,2,5,6,8|
    	int getArrayVal1 = 0;
    	int getArrayVal2 = 0;
    	int p = 0;

    	for(int i = 0; i < Array.length; i++)
    	{
			for(int x = i; x < Array.length; x++)  // 0,1
			{
				if(Array[i] > Array[x])
				{
					p = x;
					getArrayVal1 = Array[x];
					getArrayVal2 = Array[i];
				}


			}

			Array[i] = getArrayVal1;
			Array[p] = getArrayVal2;

    	}

    	for(int q = 0; q < Array.length; q++)
    	{
    		System.out.println(Array[q]);
    	}
    }


}