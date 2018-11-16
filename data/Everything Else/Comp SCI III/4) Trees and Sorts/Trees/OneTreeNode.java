//© A+ Computer Science
// www.apluscompsci.com

//tree example with one node

import static java.lang.System.*;

public class OneTreeNode
{
   public static void main( String args[] )
   {
		Treeable node = new TreeNode("10", null,null);
		out.println(node.getValue());
		out.println(node.getLeft());
		out.println(node.getRight());
   }
}