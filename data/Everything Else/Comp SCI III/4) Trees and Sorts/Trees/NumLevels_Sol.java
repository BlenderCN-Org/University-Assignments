//© A+ Computer Science
// www.apluscompsci.com

//num levels exercise

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

	public int getNumLevels()
	{
	     return getNumLevels(root);
	}
		
	private int getNumLevels(TreeNode tree)
	{
	   if(tree==null)   return 0;
	   else {
	      int numLeft = getNumLevels(tree.getLeft());
	      int numRight = getNumLevels(tree.getRight());
	      if(numLeft > numRight)
	         return 1 + numLeft; 
	      return 1 + numRight;
	   }
	}			
}

public class NumLevels_Sol
{
   public static void main( String args[] )
   {
      BinarySearchTree tree = new BinarySearchTree();

      tree.add(70);
      tree.add(100);
      tree.add(60);
	   System.out.println(tree.getNumLevels());			//outs 2      
      
      tree.add(50);
      tree.add(110);      
      tree.add(90);      
	   System.out.println(tree.getNumLevels());			//outs 3 
	   
      tree.add(10);
      tree.add(130);      
      tree.add(95);
      tree.add(190); 
      tree.add(210);      
	   System.out.println(tree.getNumLevels());			//outs 6  	      
   }
}