public class BreadthFirstDirectedPaths {
	private Queue<Integer> queue;
	private boolean[] marked;
	private int[] distTo;
	private int[] edgeTo;

	public BreadthFirstDirectedPaths(Digraph G, int s) {
		queue = new Queue<Integer>();
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		bfs(G, s);
	}

	private void bfs(Digraph G, int v) {
		marked[v] = true;
		distTo[v] = 0;
		queue.enqueue(v);
		while (!queue.isEmpty()) {
			int w = queue.dequeue();
			for (int i : G.adj(w)) {
				if(!marked[i]) {
					marked[i] = true;
					distTo[i] = distTo[w] + 1;
					edgeTo[i] = w;
					queue.enqueue(i);
				}
			}
		}
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public int distTo(int v) {
		return distTo[v];
	}

	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v)) {
			return null;
		}
		Stack<Integer> path = new Stack<Integer>();

		for (int i = v; i != s; i = edgeTo[i]) {
			path.push(i);
		}

		path.push(s);

		return path;
	}
}