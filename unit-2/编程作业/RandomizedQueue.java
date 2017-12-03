import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] stack;
	private int count;

	public RandomizedQueue() {
		stack = (Item[]) new Object[1];
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public int size() {
		return count;
	}

	private void resize(int max) {
		Item[] temp = (Item[]) new Object[max];

		for (int i = 0; i < count; i++) {
			temp[i] = stack[i];
		}

		stack = temp;
	}

	public void enque(Item item) {
		if (item == null) {
			throw new IllegalArgumentException(); 
		}

		if (count == stack.length) {
			resize(2 * count);
		}

		stack[count++] = item;
	}

	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException(); 
		}

		int n = StdRandom.uniform(count);
		Item item = stack[n];

		if (n != count - 1) {
			stack[n] = stack[count - 1];
		}

		stack[count - 1] = null;
		count--;

		if (count > 0 && count == stack.length/4) {
			resize(stack.length/2);
		}

		return item;
	}

	public Item sample() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}

		int n = StdRandom.uniform(count);
		return stack[n];	
	}

	public Iterator<Item> iterator() {
		return new stackIterator();
	}

	private class stackIterator implements Iterator<Item> {
		private Item[] copy = (Item[]) new Object[stack.length];
		private int copyn = count;

		public stackIterator() {
			for(int i = 0; i < stack.length; i++) {
				copy[i] = stack[i];
			}		
		}

		public boolean hasNext() {
			return copyn != 0;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}

			int n = StdRandom.uniform(copyn);
			Item item = copy[n];

			if (n != copyn - 1) {
				copy[n] = copy[copyn - 1];
			}

			copy[copyn - 1] = null;
			copyn--;
			return item;
		}
	}

	public static void main(String[] args) {
		
	}



}