import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity {
	
	public static void main(String [] args) throws IOException {
		ArrayList<ListNode> UnionFindList = new ArrayList<ListNode>();
		InputStream input = MainActivity.class.getResourceAsStream("/graphdata.txt");		
		BufferedReader mBufferedReader = new BufferedReader(new InputStreamReader(input));
		String mLine = "";
		
		while((mLine = mBufferedReader.readLine()) != null) {
			if(!mLine.contains("#")) {
				int parent = Integer.parseInt(mLine.substring(0, mLine.indexOf(":")));
				mLine = mLine.substring(mLine.indexOf(":")+1);
				ListNode tmpParent = new ListNode(parent);
				
				System.out.println("\n" + "Parent: " + parent);
				
				int childCount = countChildren(mLine);
				for(int i = 0; i < childCount; i++) {
					int child;
					if(i < childCount-1) {
						child = Integer.parseInt(mLine.substring(0,mLine.indexOf(",")));
						mLine = mLine.substring(mLine.indexOf(",")+1);
					} else {
						child = Integer.parseInt(mLine);
					}
					System.out.print(child + " ");
					ListNode tmpChild = new ListNode(tmpParent,child); 
				}
			}
		}
	}
	
	public static int countChildren(String childList) {
		int childrenCount = 1;
		
		if(childList.equals("")) {
			return 0;
		} else if(!childList.contains(",")) {
			return childrenCount;
		} else {
			for(int i = 0; i < childList.length(); i++) {
				if(childList.charAt(i) == ',') {
					childrenCount++;
				}
			}
		}
		
		return childrenCount;
	}
	
}
