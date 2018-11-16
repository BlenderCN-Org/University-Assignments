
import java.util.*;


public class LinkedListSample 
{
	public static void main( String [] args )
	{
		//create a node list
		NodeList nl = new NodeList();
		nl.append( 0, "500");
		nl.append(200, "199");
		nl.append( 155, "55");
		nl.printer();
		
		
		
		
	}

  
    
    
}

class NodeList
{
	public static Node headNode=null;
	public static Node tailNode=null;
	
	public void append( int pos, String d)
	{
		Node newNode= new Node(pos, d, null);
		if( headNode == null)
			headNode=newNode;
		else
			tailNode.nextNode= newNode;
		tailNode=newNode;
	}
	public void printer()
	{
		
		Node current =headNode;
		int nodeIndex=-1;
		
		do
		{
			nodeIndex++;
			System.out.println( "NODE::" + nodeIndex);
			System.out.println("Position:: " + current.position );
			System.out.println("Description:: " + current.description );
			System.out.println();
			System.out.println();
			
			current= current.nextNode;
		}while(current != null );
		
		
		
	}
}
class Node	
{
	public int position;
	public String description;
	public Node nextNode;
	
	public Node( int pos, String desc, Node nxtNode)
	{
		position = pos;
		description=desc;
		nextNode=nxtNode;
		
	}
}