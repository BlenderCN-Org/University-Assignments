

public class HashNode {
	
	public String name;
	public String type;
	public String def;
	
	public HashNode() {
		this.name = "";
		this.type = "";
		this.def = "";
	}
	
	public HashNode(String name, String type, String def) {
		this.name = name;
		this.type = type;
		this.def = def;
	}
	
	public String toString() {
		return name;
	}
	
}
