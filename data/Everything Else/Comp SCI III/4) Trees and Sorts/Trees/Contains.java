//© A+ Computer Science
// www.apluscompsci.com

//searching for values in a tree

import static java.lang.System.*;

class BinarySearchTree
{
	private TreeNode root;

	public BinarySearchTree()
	{
		root = null;
	}

	public void add(Comparable val)
	{
		//call the add helper method

	}

	private TreeNode add(Comparable val, TreeNode tree)
	{
	   if (tree == null){
	     return new TreeNode(val, null, null);
	   }
	   int dirTest = val.compareTo(tree.getValue());
	   if(dirTest<0)  				//do I go left
	     tree.setLeft(add(val, tree.getLeft()));
	   else if(dirTest>0)			//do I go right
	     tree.setRight(add(val, tree.getRight()));
	   return tree;
	}

	public boolean contains(Comparable val)
	{
		//call the contains helper method
		return false;
	}

	private boolean contains(Comparable val, TreeNode tree)
	{
		//add in the code to search the tree for a value
		
		//if val found in tree - stop and return true
		
		//check the left side 
		
		//check the right side

	   return false;
	}	
		
	public String inOrder()
	{
		//call the inOrder helper method
		return "";
	}

	private String inOrder(TreeNode tree)
	{
		//write the inorder helper method
		return "";
	}		
}

public class Contains
{
   public static void main( String args[] )
   {
      BinarySearchTree tree = new BinarySearchTree();

      tree.add(90);
      tree.add(100);
      tree.add(80);
      tree.add(50);
      tree.add(110);      
      tree.add(70);

		out.println("IN ORDER");
	   System.out.println(tree.inOrder());				//outs   50 70 80 90 100 110
	   
	   out.println("\n");
	   System.out.println(tree.contains(90));			//outs true
	   System.out.println(tree.contains(110));		//outs true
	   System.out.println(tree.contains(70));			//outs true
	   System.out.println(tree.contains(21));			//outs false
	   System.out.println(tree.contains(50));			//outs true      
   }
}