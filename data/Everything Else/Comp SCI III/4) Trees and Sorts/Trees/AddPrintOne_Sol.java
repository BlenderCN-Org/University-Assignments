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

	public void inOrder()
	{
		inOrder(root);
		System.out.println("\n\n");
	}

	//inOrder is a private helper method
	private void inOrder(TreeNode tree)
	{
		if(tree != null){
			inOrder(tree.getLeft());
			System.out.print(tree.getValue() + " ");
			inOrder(tree.getRight());
		}
	}
}

public class AddPrintOne_Sol
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
	   tree.inOrder();				///outs   50 70 80 90 100 110
   }
}