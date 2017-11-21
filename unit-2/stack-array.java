public class stack-array {
	private String[] s;
	private int N = 0;

	public stack-array(int cap) {
		s = new String[cap];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public void push(String item) {
		s[N++] = item;
	}

	public String pop() {
		return s[--N];
		/* 
		String item = s[N--];
		s[N] = null;
		return item;

		to svoid loitering */
	}
}

