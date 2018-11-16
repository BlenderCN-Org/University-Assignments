//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import static java.lang.System.*;

public class BasicList
{
   private ListNode front;
   private ListNode back;

	public BasicList( )
	{
		front = null;
		back=null;
	}

   //MODIFIER METHODS - methods that can change the list
	public void add( Comparable insertItem )
	{
		if(front==null)
		{
			back=new ListNode(insertItem,back);
			front=back;
		}
		else
		{
			ListNode temp=back;
			back = new ListNode( insertItem, null );
			temp.setNext(back);
		}
	}

	public void addFirst( Comparable insertItem)
	{
		front = new ListNode( insertItem, front );
		if(back==null)
			back=front;
	}

	public void addLast(Comparable insertItem )
	{







	}

	public Comparable remove(int index)
	{
		Comparable temp = "";








		return temp;
	}

	public boolean remove(Comparable o)
	{








		return false;
	}

	public Comparable removeFirst( )
	{
		Comparable temp = "";








		return temp;
	}

	public Comparable removeLast( )
	{
		Comparable temp = "";








		return temp;
	}

	public Comparable set(int index, Comparable element)
	{
		Comparable temp = "";








		return temp;
	}

	public void clear()
	{





	}


   //ACCESSOR METHODS - methods that do not change the list
   public boolean isEmpty()
   {
      return front==null;
   }

	public boolean equals( Comparable someList)
	{










		return false;
	}

	public boolean contains(Comparable o)
	{







		return false;
	}

	public Comparable get(int index)
	{
		Comparable val="";







		return val;
	}

	public Comparable getFirst( )
	{
		Comparable temp = "";






		return temp;
	}

	public Comparable getLast( )
	{
		Comparable temp = "";






		return temp;
	}

	public int size()
	{






		return 0;
	}

	public String toString()
	{
		String temp = "";







		return temp;
	}
}