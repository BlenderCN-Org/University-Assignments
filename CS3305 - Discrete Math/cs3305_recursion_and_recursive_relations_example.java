import java.util.*;


public class cs3305_recursion_and_recursive_relations_example {

	// Prints the list w/ custom formatting for easier reading
	private static void print(ArrayList<Integer> list) {
		System.out.print("[");
		for(int i = 0; i < list.size(); i++) {
			if(i < list.size()-1) { System.out.print(list.get(i) + ", "); }
			else { System.out.print(list.get(i)); }
		}
		System.out.println("]");
	}
	
	// Recursively returns the sum of the elements in the list 
	private static int sum_recursive(ArrayList<Integer> list) {
		if(list.size() == 0) {
			return 0;
		}
		else {
			
			return list.remove(0) + sum_recursive(list);
		}
	}
	
	// Recursively returns the number of elements in the list
	private static int length_recursive(ArrayList<Integer> list) {
		if(list.size() == 0) {
			return 0;
		}
		else {
			list.remove(0);
			return 1 + length_recursive(list);
		}
	}
	
	// Recursively reverses the list
	private static ArrayList<Integer> reverse_recursive(ArrayList<Integer> list){
		if(list.size() > 1) {
			int tmp = list.remove(0);
			reverse_recursive(list);
			list.add(tmp);
		}
		return list;
	}
	
	// Main method
	public static void main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(9); list.add(6); list.add(5); list.add(1); list.add(3);
		
		System.out.print("Original List:: ");
		print(list);
		
		System.out.println("Summation of the list:: " + sum_recursive(new ArrayList<Integer>(list)));
		System.out.println("Length of the list:: " + length_recursive(new ArrayList<Integer>(list)));
		
		list = reverse_recursive(new ArrayList<Integer>(list));
		System.out.print("Reversed List:: ");
		print(list);
	}

}
