

import static java.lang.System.*;
import java.util.*;
import java.io.*;

public class BinarySearchTree
{
	private TreeNode root;
	private int numNodes = 0;
	private int width = 0;
	private int height = 0;
	private int numLevel = 0;
	private int numLeaves = 0;
	private boolean full = true;
	private boolean sResult = false;
	private int max = Integer.MIN_VALUE;
	private int min = Integer.MAX_VALUE;
	private String rMessage = "There was no Value to remove";
	private String treeOutput = "";
	private int spaces = 1;

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
	   if(tree == null)
			return new TreeNode(val);

		Comparable treeValue = tree.getValue();
		int dirTest = val.compareTo(treeValue);


		if(dirTest <= 0)
			tree.setLeft(add(val, tree.getLeft()));
		else if(dirTest > 0)
			tree.setRight(add(val, tree.getRight()));

		return tree;
	}

   public void inOrder()
	{
		inOrder(root);
		System.out.println("\n\n");
	}

	private void inOrder(TreeNode tree)
	{
		if (tree != null){
			inOrder(tree.getLeft());
			System.out.print(tree.getValue() + " ");
			inOrder(tree.getRight());
		}
	}

	public void preOrder()
	{
		preOrder(root);
		System.out.println("\n\n");
	}

	public void preOrder(TreeNode tree)
	{
		if(tree != null)
		{
			System.out.print(tree.getValue() + " ");
			preOrder(tree.getLeft());
			preOrder(tree.getRight());
		}
	}

	public void postOrder()
	{
		postOrder(root);
		System.out.println("\n\n");
	}

	public void postOrder(TreeNode tree)
	{
		if(tree != null)
		{
			postOrder(tree.getLeft());
			postOrder(tree.getRight());
			System.out.print(tree.getValue() + " ");

		}
	}

	public void reverseOrder()
	{
		reverseOrder(root);
		System.out.println("\n\n");
	}

	public void reverseOrder(TreeNode tree)
	{
		if(tree != null)
		{
			reverseOrder(tree.getRight());
			System.out.print(tree.getValue() + " ");
			reverseOrder(tree.getLeft());
		}
	}

	public void numLevels()
	{
		numLevels(root);
		System.out.println (numLevel);

	}

	public void numLevels(TreeNode tree)
	{
		if (tree != null)
		{
			if(tree.getLeft() != null)
			{
				numLevels(tree.getLeft());

			}
			if(tree.getRight()!= null)
			{
				numLevels(tree.getRight());

			}
			if(tree.getRight() != null || tree.getLeft() != null)
			{
				numLevel++;
			}
		}
	}

	public void remove(int i)
	{
		remove(root,i);
		System.out.println (rMessage);
	}
	public void remove(TreeNode tree, int i)
	{
		if(tree != null)
		{
			if((int)tree.getValue() == i)
			{
				tree.setValue(null);
			}
			else
			{
				remove(tree.getRight(),i);
				remove(tree.getLeft(),i);
			}
		}
	}

	public void getNumLeaves()
	{
		getNumLeaves(root);
		System.out.println (numLeaves);
	}

	public void getNumLeaves(TreeNode tree)
	{
		if(tree != null)
		{
			if(tree.getRight() == null && tree.getLeft() == null)
			{
				numLeaves++;
			}

			getNumLeaves(tree.getRight());
			getNumLeaves(tree.getLeft());
		}
	}

	public void getNumNodes()
	{
		getNumNodes(root);
		System.out.print(numNodes);
	}

	public void getNumNodes(TreeNode tree)
	{
		if(tree != null)
		{
			numNodes++;
			getNumNodes(tree.getRight());
			getNumNodes(tree.getLeft());
		}
	}

	public void getHeight()
	{
		getHeight(root);
		out.print(height-1);
	}

	public void getHeight(TreeNode tree)
	{
		if (tree != null)
		{
			if(tree.getLeft() != null)
			{
				getHeight(tree.getLeft());

			}
			if(tree.getRight()!= null)
			{
				getHeight(tree.getRight());

			}
			if(tree.getRight() != null || tree.getLeft() != null)
			{
				height++;
			}
		}
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

	public void isFull()
	{
		isFull(root);
		System.out.println (full);
	}

	public void isFull(TreeNode tree)
	{
		if (tree != null)
		{
			if((tree.getRight() == null && tree.getLeft() != null) || (tree.getRight() != null && tree.getLeft() == null))
			{
				full = false;
			}
			else
			{
				isFull(tree.getRight());
				isFull(tree.getLeft());
			}
		}

	}


	public void search(int i)
	{
		search(root,i);
		System.out.println(sResult);
	}

	public void search(TreeNode tree, int i)
	{
		if (tree != null)
		{
			if((int)tree.getValue() == i)
			{
				sResult = true;
			}
			else
			{
				search(tree.getLeft(),i);
				search(tree.getRight(),i);
			}
		}
	}

	public void getNumLevels()
	{
		getNumLevels(root);
		System.out.println (numLevel);
	}

	private void getNumLevels(TreeNode tree)
	{
		if (tree != null)
		{
			if(tree.getLeft() != null)
			{
				getNumLevels(tree.getLeft());

			}
			if(tree.getRight()!= null)
			{
				getNumLevels(tree.getRight());

			}
			if(tree.getRight() != null || tree.getLeft() != null)
			{
				numLevel++;
			}
		}
	}

	public void mNode()
	{
		mNode(root);
		System.out.println(max);
	}
	public void mNode(TreeNode tree)
	{
		if(tree!=null)
		{
			if((int)tree.getValue() > max)
			{
				max = (int)tree.getValue();
			}

			mNode(tree.getRight());
			mNode(tree.getLeft());
		}
	}

	public void miNode()
	{
		miNode(root);
		System.out.println(min);
	}
	public void miNode(TreeNode tree)
	{
		if(tree!=null)
		{
			if((int)tree.getValue() < min)
			{
				min = (int)tree.getValue();
			}

			miNode(tree.getRight());
			miNode(tree.getLeft());
		}
	}
	public void printTree(int depth, int w)
	{
		for(int i = 1; i <= depth; i++)
		{
			String levelNodes = printTree(root,i);
			String spaces = "";
			String spaces2 = "";

			if(depth == 1)
			{
				for(int t = 0; t < w+1; t++)
				{
					if(t < 3)
					{
						spaces += " ";
					}
					else
					{
						spaces2 += " ";
					}

				}
			}

			if(i != 3)
			{
				for(int p = 0; p < i; p++)
				{
					spaces += " ";
				}
			}
			System.out.print(spaces + levelNodes + spaces2 + "\n");
		}
	}

	public String printTree(TreeNode tree,int level)
	{
		if(tree == null)
		{
			return "";
		}
		if(level == 1)
		{
			return tree.getValue() + " ";
		}
		else if(level > 1)
		{
			String left = printTree(tree.getLeft(), level-1);
			String right = printTree(tree.getRight(), level-1);

			return left + right;
		}

		else
		{
			return "";
		}
	}
}