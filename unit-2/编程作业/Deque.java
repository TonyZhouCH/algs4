import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	 private int count;
	 private Node first,last;

	 private class Node {
	 	Item item;
	 	Node L;
	 	Node R;
	 }

	 public Deque() {
	 	first = new Node();
	 	last = new Node();
	 }
	 
	 public boolean isEmpty() {
	 	return count == 0;
	 }

	 public int size() {
	 	return count;
	 }

	 public void addFirst(Item item) {
	 	if (item == null) {
	 		throw new NullPointerException();
	 	}

	 	Node oldfirst = first;
	 	first = new Node();
	 	first.item = item;  //first.L and first.R are 'null', no need to state;
	 	first.R = oldfirst;

	 	if(isEmpty()) {
	 		last = first;
	 	} else {
	 		oldfirst.L = first;
	 	}

	 	count++;
	 }

	 public void addLast(Item item) {
	 	if (item == null) {
	 		throw new NullPointerException();
	 	}

	 	Node oldlast = last;
	 	last = new Node();
	 	last.item = item;
	 	last.L = oldlast;

	 	if(isEmpty()) {
	 		first = last;
	 	} else {
	 		oldlast.R = last;
	 	}

	 	count++;

	 }

	 public Item removeFirst() {
	 	if(isEmpty()) {  
            throw new NoSuchElementException();  
        }  

	 	Item item = first.item;
	 	first = first.R;
	 	count--;

	 	if(isEmpty()) {
	 		last = null;
	 	} else {
	 		first.L = null;
	 	}
	 	
	 	return item;
	 }

	 public Item removeLast() {
	 	if(isEmpty()) {
	 		throw new NoSuchElementException(); 
	 	}

	 	Item item = last.item;
	 	last = last.L;
	 	count--;

	 	if(isEmpty()) {
	 		first = null;
	 	} else {
	 		last.R = null;
	 	}
	 	return item;
	 }

	 public Iterator<Item> iterator() {
	 	return new ListIterator(first);
	 }

	 private class ListIterator implements Iterator<Item> {
	 	private Node current;

	 	public ListIterator(Node first) {
	 		current = first;
	 	}

	 	public boolean hasNext() {
	 		return current.R != null;
	 	}

	 	public void remove() {
	 		throw new UnsupportedOperationException(); 
	 	}

	 	public Item next() {
	 		if (!hasNext()) {
	 			throw new NoSuchElementException(); 
	 		}

	 		Item item = current.item;
	 		current = current.R;
	 		return item;

	 	}
	 }

	 public static void main(String [] args) {

	 }
}