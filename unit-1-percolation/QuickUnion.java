public class QuickUnion {
	private int[] id;
	private int count;

	public QuickUnion(int N) {
		count = N;
		id = new int[N];

		for (int i = 1; i < N; i++) {
			id[i] = i;
		}
	}

	public int count() {
		return count;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	private int find(int p) {
		while (p != id[p]) {
			p = id[p];
		}
		return p;
	}

	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);

		if (qRoot == pRoot) {
			return;
		}

		id[pRoot] = qRoot;

		count--;
	}

	public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickUnion uf = new QuickUnion(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }

}