//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;

public class ListFunHouse
{
	//this method will print the entire list on the screen
   public static void print(ListNode list)
   {

   	while(list != null)
   	{
   		out.println(list.getValue());
   		list = list.getNext();
   	}

   }

	//this method will return the number of nodes present in list
	public static int nodeCount(ListNode list)
	{
   	int count=0;

   		while(list != null)
   		{

   			count++;

   			list = list.getNext();
   		}
		return count;
	}

	//this method will create a new node with the same value as the first node and add this
	//new node to the list.  Once finished, the first node will occur twice.
	public static void doubleFirst(ListNode list)
	{
		Linkable node = new ListNode(list.getValue(),list);
		list = node;


	}

	//this method will create a new node with the same value as the last node and add this
	//new node at the end.  Once finished, the last node will occur twice.
	public static void doubleLast(ListNode list)
	{
   		ListNode prev=null;
	}

	//method skipEveryOther will remove every other node
	public static void skipEveryOther(ListNode list)
	{
	}

	//this method will set the value of every xth node in the list
	public static void setXthNode(ListNode list, int x, Comparable value)
	{
		int count=1;
	}

	//this method will remove every xth node in the list
	public static void removeXthNode(ListNode list, int x)
	{
		int count=1;
	}
}