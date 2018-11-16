//© A+ Computer Science
// www.apluscompsci.com

//tree example linking nodes together

import static java.lang.System.*;

public class TreeTwo
{
   public static void main( String args[] )
   {
		TreeNode x = new TreeNode("10",null,null);
		TreeNode y = new TreeNode("25", null,null);
		TreeNode z = new TreeNode("20", x, y);

		out.println(z.getValue());
		out.println(z.getLeft().getValue());
		out.println(z.getRight().getValue());
   }
}

