

public class HashNode {
	
	public String name; // Key
	public String type; // Part of Speech
	public String def; // Definition
	
	// Default Constructor
	public HashNode() {
		this.name = "";
		this.type = "";
		this.def = "";
	}
	
	// Constructor taking all input at once
	public HashNode(String name, String type, String def) {
		this.name = name;
		this.type = type;
		this.def = def;
	}
	
	// toString, mainly for debugging
	public String toString() {
		return name;
	}
	
}
