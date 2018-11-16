//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.util.Queue;
import java.util.Stack;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;

public class PalinList
{
	private Queue<String> queue;
	private Stack<String> stack;
	public String[] Arr;

	public PalinList()
	{
		setList("");

	}

	public PalinList(String list)
	{
		Arr = list.split(" ");
	}

	public void setList(String list)
	{
		Arr = list.split(" ");
	}

	public String toString()
	{
		for(int x = 0; x < Arr.length; x++)
		{
			System.out.print (Arr[x]);
		}

		return "DONE";
	}

	public boolean isPalin()	{
		//String listJuan = "one two three two one";



		boolean Test = true;
		int i = 1;
		int p;
		/*
		if(Arr.length%2 == 0)
		{
			p=0;
		}
		else
		{
			p=1;
		}
		*/

		for(int x = 0; x < Arr.length/2; x++)
		{
			if(Arr[x].equals(Arr[Arr.length-i]))
			{
				i++;
			}
			else
			{
				Test = false;
			}


		}

		System.out.println("");
		return Test;
	}


	//write a toString method
}