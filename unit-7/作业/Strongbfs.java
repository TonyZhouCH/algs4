package edu.princeton.cs.algs4;
import edu.princeton.cs.algs4.In;
import java.util.*;


public class Strongbfs {
	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;
	private int s;

	public Strongbfs(Digraph G, int s) {
		this.s = s;
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		distTo = new int[G.V()];

		for (int v = 0; v < G.V(); v++) {
			distTo[v] = INFINITY;
		}

		bfs(G, s);
	}

	public Strongbfs(Digraph G, Iterable<Integer> sources) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		distTo = new int[G.V()];

		for (int v = 0; v < G.V(); v++) {
			distTo[v] = INFINITY;
		}

		bfs(G, sources);
	}



	private void bfs(Digraph G, int s) {
		Queue<Integer> queue = new Queue<Integer>();
		marked[s] = true;
		distTo[s] = 0;
		queue.enqueue(s);
		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			for (int m : G.adj(v)) {
				if (!marked[m]) {
					marked[m] = true;
					edgeTo[m] = v;
					distTo[m] = distTo[v] + 1;
					queue.enqueue(m);
				}
			}
		}
	}

	private void bfs(Digraph G, Iterable<Integer> sources) {
		Queue<Integer> queue = new Queue<Integer>();
		for (int s : sources) {
			marked[s] = true;
			distTo[s] = 0;
			queue.enqueue(s);
		}
		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			for (int m : G.adj(v)) {
				if (!marked[m]) {
					marked[m] = true;
					edgeTo[m] = v;
					distTo[m] = distTo[v] + 1;
					queue.enqueue(m);
				}
			}
		}
	}

	public  boolean hasPathTo(int v) {
		return marked[v];
	}

	public boolean[] getMarked() {   //newly added
		return this.marked;
	}

	public int distTo(int v) {
		return distTo[v];
	}

	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v)) {
			return null;
		}

		Stack<Integer> path = new Stack<Integer>();
		for (int x = v; x != s; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(s);
		return path;
	}

	public static void main(String[] args) {

   }


}