public class weightedQuickUnion {
	private int[] id;
	private int[] sz;
	private int count;

	public weightedQuickUnion(int N) {
		count = N;
		id = new int[N];
		sz = new int[N];

		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
			
		for (int i = 0; i < N; i++) {
			sz[i] = 1;
		}
	}

	public int count() {
		return count;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	private int find(int p) {
		/* int temp = p; */
		
		while(id[p] != p) {
			p = id[p];
		}

		/* while(id[temp] != temp) {
			temp = id[temp];
			id[temp] = p;
		} */

		return p;
	}

	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);

		if (pRoot == qRoot){
			return;
		}

		if (sz[pRoot] < sz[qRoot]) {
			id[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
		} else {
			id[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
		}

		count--;
	}

	public static void main(String[] args) {
        long a=System.currentTimeMillis();
        int n = StdIn.readInt();
        weightedQuickUnion uf = new weightedQuickUnion(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
        System.out.println("\rTime : "+(System.currentTimeMillis()-a)/1000f+" s ");
    }

}