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
		if (tree == null)
			tree = new TreeNode(val, null, null);
		else if(val.compareTo(tree.getValue())<0)
			tree.setLeft(add(val, tree.getLeft()));
		else if(val.compareTo(tree.getValue())>0)
			tree.setRight(add(val, tree.getRight()));
		return tree;
	}

	public String inOrder()
	{
		return "";
	}

	private String inOrder(TreeNode tree)
	{
		//write the code to return a list
		//of all items in the tree in order


		return "";
	}
	
	public String postOrder()
	{
		return "";
	}

	//add in a private helper method for postOrder
		
}

public class AddPrintTwo
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
	   
		out.println("\n\nPOST ORDER");
	   System.out.println(tree.postOrder());			//outs   70 50 80 110 100 90	   
   }
}