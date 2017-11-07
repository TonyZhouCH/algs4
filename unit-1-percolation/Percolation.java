import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private int count;
	private int num;
	private int tail;
	private WeightedQuickUnionUF id;
	private WeightedQuickUnionUF idOnlyTop;
	private boolean[] state;

	public Percolation(int n) {
		if (n < 1) {
			throw new IndexOutOfBoundsException("valid input!!");
		}

		num = n;
		tail = n * n;
		id = new WeightedQuickUnionUF(tail + 2);
		idOnlyTop = new WeightedQuickUnionUF(tail + 1);

		state = new boolean[tail + 1];
	}

	private void validate(int row, int col) {
		if (row < 1 || row > num) {
			throw new IndexOutOfBoundsException("row index out of bounds");
		}

		if (col < 1 || col > num) {
			throw new IndexOutOfBoundsException("col index out of bounds");
		}
	}
	
	 private int xyTo1d(int row, int col) {
	 	validate(row, col);

	 	return (row - 1) * num + col;
	 }

	 public void open(int row, int col) {
	 	validate(row, col);

	 	int i = xyTo1d(row, col);

	 	state[i] = true;
	 	count++;

	 	
	 	if (row == 1) {
	 		id.union(i, 0);
	 		idOnlyTop.union(i, 0);
	 	}

	 	if (row == num) {
	 		id.union(i, tail + 1);
	 	}

	 	int m;

	 	if (row > 1) {
	 		m = xyTo1d(row - 1, col);

	 		if (state[m] == true) {
	 			id.union(i, m);
	 			idOnlyTop.union(i, m);
	 		}
	 	}

	 	if (row < num) {
	 		m = xyTo1d(row + 1, col);

	 		if (state[m] == true) {
	 			id.union(i, m);
	 			idOnlyTop.union(i, m);
	 		}
	 	}

	 	if (col > 1) {
	 		m = xyTo1d(row, col - 1);

	 		if (state[m] == true) {
	 			id.union(i, m);
	 			idOnlyTop.union(i, m);
	 		}
	 	}

	 	if (col < num) {
	 		m = xyTo1d(row, col + 1);

	 		if (state[m] == true) {
	 			id.union(i, m);
	 			idOnlyTop.union(i, m);
	 		}
	 	}
	 }

	 public boolean isOpen(int row, int col) {
	 	validate(row, col);
	 	return state[xyTo1d(row, col)];
	 }

	 public boolean isFull(int row, int col) {
	 	validate(row, col);
	 	int q = xyTo1d(row, col);

	 	if (idOnlyTop.connected(q, 0)) {  /* id.find(0) == idOnlyTop.find(q)*/
	 		return true;
	 	} 
	 	return false;
	 }

	 public int numberOfOpenSites() {
	 	return count;
	 }

	 public boolean percolates() {
	 	return id.connected(0, tail+1);

	 	/* for(int r = (num * (num - 1 ) + 1); r <= tail; r++) {
	 		if (idOnlyTop.connected(r, 0)) {
	 			return true;
	 		} 
	 	}

	 	return false; */
	 }

}