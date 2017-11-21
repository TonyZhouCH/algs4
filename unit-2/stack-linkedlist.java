public class stack-linkedlist {
	private Node first = null;

	private class node {
		string item;
		node next;
	}

	public boolean isEmpty() {
		return first = null; 
	}

	public void push(String item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}

	public String pop() {
		String item = first.item;
		first = first.next;
		return item;
	}
}