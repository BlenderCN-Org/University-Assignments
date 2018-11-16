import java.util.*;



public class ArrayLabs {

    public static void main(String[]args)
    {

	Scanner kb = new Scanner(System.in);

	System.out.println("Please choose your Program");
	System.out.println("1. Finding The Largest Numerical Value::");
	System.out.println("2. Finding The Longest Word::");
	System.out.println("3. Sorting The Data In Alphabetical Order::");
	System.out.println("4. Reversing The Elements::");
	int UserInput = kb.nextInt();

		if(UserInput == 1)
			{
				Largest();
			}

		else if(UserInput == 2)
			{
				Longest();
			}

		else if(UserInput == 3)
			{
				Alpha();
			}

  		else if(UserInput == 4)
			{
				Reverse();
			}
		else
			{
				System.out.println("");
				System.out.println("Pick a correct number baka!!! (Numbers 1-4) ");
				System.out.println("");
				System.out.println("////////////////////////////////////////////////////////////////////////////////");
				System.out.println("");

				RetryMain();
			}


    }



	public static void Largest()
	{
		System.out.println("");
		System.out.println("////////////////////////////////////////////////////////////////////////////////");
		System.out.println("");

		int Variable = 0;

		int[] Array = {1,2,3,4,5,6,7,8,9,10};
		System.out.println("You have selected the [Largest] Program");


		for(int i=0; i<Array.length; i++)
		{
			if(Array[i] > Variable)
			{
				Variable = Array[i];
			}

		}


		System.out.println("The Largest Number is:: " + Variable);
	}

	public static void Longest()
	{
		System.out.println("");
		System.out.println("////////////////////////////////////////////////////////////////////////////////");
		System.out.println("");
		String Variable = "";
		String[] Array = new String[] {"Java","C++","Python","CryEngine","UnrealEngine"};
		System.out.println("You have selected the [Longest] Program");


		for(int i=0; i<Array.length; i++)
		{
			if(Array[i].length() > Variable.length())
			{
				Variable = Array[i];
			}

		}

		System.out.println("The Longest Word is:: " + Variable);
	}

	public static void Alpha()
	{

		System.out.println("");
		System.out.println("////////////////////////////////////////////////////////////////////////////////");
		System.out.println("");
		String Variable = "                                                                 ";
		String[] Array = new String[] {"Java","C++","Python","CryEngine","UnrealEngine"};
		System.out.println("You have selected the [Alpha] Program");
		Variable = Array[1];

		for(int i=0; i<Array.length; i++)
		{
		Variable.compareTo(Array[i]);
		}

		System.out.println("The Alpha Word is:: " + Variable);

	}

	public static void Reverse()
	{
		System.out.println("");
		System.out.println("////////////////////////////////////////////////////////////////////////////////");
		System.out.println("");
		int Variable = 0;
		int[] Array = new int[] {0,0,3,0,5,6,7,0,9,0};
		System.out.println("You have selected the [Reverse] Program");
		String Stuff = "";

		for(int i=0; i<Array.length; i++)
		{
			if(Array[i] == 0)
			{
				Array.remove[i];


				}
			}
		}

		for(int x=0; int x < Array.length; x ++)
		{
			Stuff = Stuff + (String)Array[i];
		}
		
		System.out.println(Stuff);

	}

	public static void RetryMain()
	{

		Scanner kb = new Scanner(System.in);

	System.out.println("Please choose your Program");
	System.out.println("1. Finding The Largest Numerical Value::");
	System.out.println("2. Finding The Longest Word::");
	System.out.println("3. Sorting The Data In Alphabetical Order::");
	System.out.println("4. Reversing The Elements::");
	int UserInput = kb.nextInt();

		if(UserInput == 1)
			{
				Largest();
			}

		else if(UserInput == 2)
			{
				Longest();
			}

		else if(UserInput == 3)
			{
				Alpha();
			}

  		else if(UserInput == 4)
			{
				Reverse();
			}
		else
			{
				System.out.println("");
				System.out.println("Pick a correct number baka!!! (Numbers 1-4) ");
				System.out.println("");
				System.out.println("////////////////////////////////////////////////////////////////////////////////");
				System.out.println("");

				RetryMain();
			}

	}




}