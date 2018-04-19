public class DirectedDFS {
	private Boolean[] marked;

	public DirectedDFS(Digraph G, int s) {
		marked = new Boolean[G.V()];
		dfs(G, s);
	}

	private void dfs(Digraph G, int v) {
		marked[v] = true;
		for (int w : adj[v]) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
	}

	public Boolean visited(int v) {
		return marked[v];
	}
}