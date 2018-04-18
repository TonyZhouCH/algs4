public class Graph {
	private int V;
	private Bag<Integer>[] adj;
	private int E;

	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[v];  //由于数组不支持泛型，需要强制进行转化，为Bag类型的数组
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}

	public Graph(In in) {
		this(in.readInt());
		int E = readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	} 

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public static int degree(Graph G, int v) {
		int degree = 0;
		for (int w : G.adj(v)) {
			degree++;
		}

		return degree;
	}

	public static int maxDegree(Graph G) {
		int maxDegree = 0;
		for (int v = 0; v < G.V(); v++) {
			if (maxDegree < degree(G, v)) {
				maxDegree = degree(G, v);
			}
		}

		return maxDegree;
	}

	public static double averageDegree(Graph) {
		return 2.0 * G.E() / G.V();
	}

	public static int numberOfSelfLoops(Graph G) {
		int count = 0; 
		for (int v = 0; v < G.V(); v++) {
			for (int w : adj[v]) {
				if (w == v) {
					count++;
				}
			}
		}

		return count/2;   //each edge counted twice
	}
}