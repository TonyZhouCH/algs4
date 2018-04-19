public class Digraph {
	private int V;
	private int E;
	private Bag<Integer>[] adj;

	public Digraph (int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];  //数组不支持泛型，需要强制转换
		for (int i =0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
		E++;
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	public Digraph reverse() {
		Digraph R = new Digraph(V);
		for (int i = 0; i < V; v++) {
			for (int w : adj(v)) {
				R.addEdge(w, v);
			}
		}

		return R;
	}
}