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
		//add the code to add val to the tree


		//check for null
		
		
		//do you go left
		
		
		//do you go right


		return null;
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

public class AddPrintOne
{
   public static void main( String args[] )
   {
      BinarySearchTree tree = new BinarySearchTree();

      tree.add(90);
      tree.add(100);
      tree.add(80);
      tree.add(70);

		out.println("IN ORDER");
	   tree.inOrder();				///outs   50 70 80 90 100 110
   }
}