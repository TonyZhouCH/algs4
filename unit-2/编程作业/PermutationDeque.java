 import edu.princeton.cs.algs4.StdIn;
  import edu.princeton.cs.algs4.StdOut;

  public class PermutationDeque {
  	public static void main(String[] args) {
  		 Deque<String> q = new Deque<String>();
  		 int k = Integer.parseInt(args[0]);

  		 while (!StdIn.isEmpty()) {
  		 	String item = StdIn.readString();
  		 	q.addLast(item);
  		 }

  		 while(k > 0) {
  		 	StdOut.println(q.removeFirst());
  		 	k--;
  		 }
  	}
  }