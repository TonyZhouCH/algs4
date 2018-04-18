public class BreadthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private int s;

	public BreadthFirstPaths(Graph G, int s) {
		this.s = s;
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()]
		bfs(G, s)
	}
	
	private void bfs(Graph G, int s) {
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(s);
		marked[s] = true;

		while (!q.isEmpty()) {
			int v = q.dequeue();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					q.enqueue(w);
					marked[w] = true;
					edgeTo[w] = v;
				}
				
			}
		}
	}
}