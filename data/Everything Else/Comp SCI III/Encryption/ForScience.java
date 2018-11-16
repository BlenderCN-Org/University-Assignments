import java.util.*;
import java.io.*;
import java.lang.*;
import java.lang.System.*;


public class ForScience {

		private static File JumpVal = new File("JumpVal.txt");
    	private static File MessageVal = new File("MessageVal.txt");

    public static void main (String [] args) throws IOException
    {
		Scanner kb = new Scanner(System.in);

		System.out.println("Please Choose a Program to Run & Test");
		System.out.println("1: Encryption");
		System.out.println("2: Decryption");
		System.out.println("");

		if(kb.nextLine().equals("1"))
		{
			System.out.println("");
			Encryption();
		}
		else
		{
			System.out.println("");
			Decryption();
		}

    }


    public static void Encryption() throws IOException
    {
		Scanner kb = new Scanner(System.in);
		int Jump;
		int Index = 0;
		String Code = "";

		System.out.println("Please Enter a Code::  ");

		String UserInput = kb.nextLine();

		char[] c_arr =  UserInput.toCharArray();

		numSwap(c_arr,UserInput);


    }

    public static void numSwap(char[] c,String code) throws IOException
    {
    	String Code = "";
    	int over = 0;
    	int jump = 0;

    	JumpVal.createNewFile();
    	FileWriter j = new FileWriter(JumpVal);

    	MessageVal.createNewFile();
    	FileWriter m = new FileWriter(MessageVal);

    	if(code.equals("::END::"))
    	{
    		j.close();
			m.close();
			System.exit(0);
    	}

		for(int i = 0; i < c.length; i++)
		{
			jump = (int)(Math.random() * (24-0));
    		//System.out.println (jump);
    		j.write(jump + "" + " ");

			if(c[i] + 0 >= 65 && c[i] + 0 <= 90) // Uppercase
			{
				if((c[i] + 0) + jump > 90) // If is greater than max Upper
				{
					over = (c[i] + 0) + jump;
					over = over - 90;
					over = over + 65;
					c[i] = (char)(over);
					Code = Code + (c[i] + "");
					//System.out.println (c[i]);
				}
				else
				{
					over = (c[i] + 0) + jump;
					c[i] = (char)(over);
					Code = Code + (c[i] + "");
				}
			}
			else if(c[i] + 0 >= 97 && c[i] + 0 <= 122) // Lowercase
			{
				if((c[i] + 0) + jump > 122) // If is greater than max Upper
				{
					over = (c[i] + 0) + jump;
					over = over - 122;
					over = over + 97;
					c[i] = (char)(over);
					Code = Code + (c[i] + "");
					//System.out.println (c[i]);
				}
				else
				{
					over = (c[i] + 0) + jump;
					c[i] = (char)(over);
					Code = Code + (c[i] + "");
				}
			}
			else // Symbols
			{
				Code = Code + (c[i] + "");
			}
		}

		m.write(Code);
		System.out.println (Code);
		j.close();
		m.close();
		Encryption();
    }

	public static void Decryption() throws IOException
	{
		Scanner xn = new Scanner (System.in);

		Scanner jb = new Scanner(new File("JumpVal.txt"));
		Scanner kb = new Scanner(new File("MessageVal.txt"));

		ArrayList<Integer> numVal = new ArrayList<Integer>();

		String Code = "";

		while(jb.hasNextInt() == true)
		{
			numVal.add(jb.nextInt());
		}
		//System.out.println (numVals);

		System.out.println ("Decrypting Last Message...");

		Code = kb.nextLine();
		//System.out.println (Code);

		char[] c_arr =  Code.toCharArray();

		decSwap(c_arr, numVal);
	}

	public static void decSwap(char[] c, ArrayList<Integer> numVal)
	{
		int over = 0;
		int jump = 0;
		String Code = "";

		for(int i = 0; i < c.length; i++)
		{
			if(c[i] + 0 >= 65 && c[i] + 0 <= 90) // Uppercase
			{
				jump = numVal.get(i);
				if((c[i] + 0) - jump < 65) // If is greater than max Upper
				{
					over = (c[i] + 0) - jump;
					over = over + 90;
					over = over - 65;
					c[i] = (char)(over);
					Code = Code + (c[i] + "");
					//System.out.println (c[i]);
				}
				else
				{
					over = (c[i] + 0) - jump;
					c[i] = (char)(over);
					Code = Code + (c[i] + "");
				}
			}
			else if(c[i] + 0 >= 97 && c[i] + 0 <= 122) // Lowercase
			{
				jump = numVal.get(i);
				if((c[i] + 0) - jump < 97) // If is greater than max Upper
				{
					over = (c[i] + 0) - jump;
					over = over + 122;
					over = over - 97;
					c[i] = (char)(over);
					Code = Code + (c[i] + "");
					//System.out.println (c[i]);
				}
				else
				{
					over = (c[i] + 0) - jump;
					c[i] = (char)(over);
					Code = Code + (c[i] + "");
				}
			}
			else // Symbols
			{
				Code = Code + (c[i] + "");
			}
		}		

		System.out.println (Code);
	}


}