//© A+ Computer Science
// www.apluscompsci.com

//width exercise

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

	public int getWidth()
	{
	     return getWidth(root.getLeft()) + getWidth(root.getRight()) + 1;
	}
		
	private int getWidth(TreeNode tree)
	{
	   if(tree==null)   return 0;
	   else {
	      int numLeft = getWidth(tree.getLeft());
	      int numRight = getWidth(tree.getRight());
	      if(numLeft > numRight)
	         return 1 + numLeft; 
	      return 1 + numRight;
	   }
	}			
}

public class NumWidth_Sol
{
   public static void main( String args[] )
   {
      BinarySearchTree tree = new BinarySearchTree();

		//			70
		//		60     100
      tree.add(70);
      tree.add(100);
      tree.add(60);
	   System.out.println(tree.getWidth());			//outs 3      
      
      
		//			70
		//		60     100
		//50       90   110
      tree.add(50);
      tree.add(110);      
      tree.add(90);      
	   System.out.println(tree.getWidth());			//outs 5 
	   
	   
		//				70
		//	   	60     100
		//   50       90   110
		//10	      95       130
		//                     190
		//                       210
      tree.add(10);
      tree.add(130);      
      tree.add(95);
      tree.add(190); 
      tree.add(210);      
	   System.out.println(tree.getWidth());			//outs 9  	      
   }
}