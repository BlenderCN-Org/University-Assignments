//© A+ Computer Science
// www.apluscompsci.com

//double linked list example

import static java.lang.System.*;

class DoublyNode
{
   private Comparable data;
   private DoublyNode next;
   private DoublyNode prev;

   public DoublyNode(Comparable dat, DoublyNode prv, DoublyNode nxt)
   {
      data=dat;	
      prev=prv;
      next=nxt;
   }
   public void setNext(DoublyNode nxt)
   {
   	next=nxt;
   }
   public void setPrevious(DoublyNode prv)
   {
   	prev=prv;
   }   
   public DoublyNode getNext()
   {
   	return next;
   }
   public DoublyNode getPrevious()
   {
   	return prev;
   }  
   public Comparable getValue()
   {
   	return data;
   }     
}


public class DoublyNodeOne
{
   public static void main( String args[] )
   {
		DoublyNode first = new DoublyNode(2,null,null);
		DoublyNode last = new DoublyNode(6,null,first);
		DoublyNode middle = new DoublyNode(4,first,last);	
		first.setPrevious(last);	
		first.setNext(middle);		
		
		out.println(first.getNext().getValue());	
		out.println(first.getPrevious().getValue());			
   }
}