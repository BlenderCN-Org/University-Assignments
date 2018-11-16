// preOrder, postOrder, inOrder, revOrder, getNumLeaves, getNumLevels, getWidth, getHeight, getNumNodes, toString, isFull.

import static java.lang.System.*;
import java.util.*;
import java.io.*;

public class Lab17a
{
   public static void main( String args[] )
   {
   	  	BinarySearchTree k = new BinarySearchTree();
		k.add(90);
		k.add(80);
		k.add(70);
		k.add(85);
		k.add(100);
		k.add(98);
		k.add(120);


		System.out.print("inOrder:: ");
		k.inOrder();
		System.out.print("preOrder:: ");
		k.preOrder();
		System.out.print("postOrder:: ");
		k.postOrder();
		System.out.print("reverseOrder:: ");
		k.reverseOrder();
		System.out.print("Height:: ");
		k.getHeight();
		System.out.println ();
		System.out.println ();
		System.out.print("Width:: ");
		int w = k.getWidth();
		System.out.print(k.getWidth());
		System.out.println ();
		System.out.println ();
		System.out.print("Number of Leaves:: ");
		k.getNumLeaves();
		System.out.println ();
		System.out.print("Number of Nodes:: ");
		k.getNumNodes();
		System.out.println ();
		System.out.println ();
		System.out.print("Number of Levels:: ");
		k.getNumLevels();
		System.out.println ();
		System.out.print("Outprinted tree as a String:: ");
		k.inOrder();
		System.out.print("isFull:: ");
		k.isFull();
		System.out.println ();
		k.printTree(3,w);
		System.out.println ();

   }
}