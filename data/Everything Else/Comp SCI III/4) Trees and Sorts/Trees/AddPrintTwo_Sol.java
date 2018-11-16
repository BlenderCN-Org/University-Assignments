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
		return inOrder(root);
	}

	private String inOrder(TreeNode tree)
	{
		if(tree != null){
			return inOrder(tree.getLeft()) + tree.getValue() + " " + inOrder(tree.getRight());
		}
		return "";
	}
	
	public String postOrder()
	{
		return postOrder(root);
	}

	private String postOrder(TreeNode tree)
	{
		if(tree != null){
			return postOrder(tree.getLeft()) + postOrder(tree.getRight()) + tree.getValue() + " ";
		}
		return "";
	}	
}

public class AddPrintTwo_Sol
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