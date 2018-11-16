//© A+ Computer Science
// www.apluscompsci.com

//add and print tree example

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
		root = add(val, root);
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
		return contains(val,root);
	}

	private boolean contains(Comparable val, TreeNode tree)
	{
	   if(tree != null)
	   {
	      int dirTest = val.compareTo(tree.getValue());
	      if(dirTest == 0 ) 
	         return true;
	      else if (dirTest < 0)
	         return contains(val, tree.getLeft());
	      else if (dirTest > 0)
	         return contains(val, tree.getRight());
	   }
	   return false;
	}	
		
	public String inOrder()
	{
		return inOrder(root);
	}

	private String inOrder(TreeNode tree)
	{
		if(tree != null){
			return inOrder(tree.getLeft()) + tree.getValue() + " " + inOrder(tree.getRight());
		}
		return "";
	}		
}

public class Contains_Sol
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