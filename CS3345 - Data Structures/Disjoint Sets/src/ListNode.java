
public class ListNode {
	ListNode localParent;
	int Payload;
	
	public ListNode(int Payload) {
		this.localParent = this;
		this.Payload = Payload;
	}
	
	public ListNode(ListNode localParent, int Payload) {
		this.localParent = localParent;
		this.Payload = Payload;
	}
}
