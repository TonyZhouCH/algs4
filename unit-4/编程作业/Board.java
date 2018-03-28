import java.util.ArrayList;

public class Board {

	private int n;
	private int[][] blocks;

	/*
	public Board (int[][] blocks) {
		this.blocks = blocks;
	}

	public int dimension() {
		return n;
	} */

	public Board(int[][] inBlocks) {
          // construct a board from an n-by-n array of blocks
         // (where blocks[i][j] = block in row i, column j)
          n = inBlocks.length;
          blocks = new int[n][n];
          copy(blocks, inBlocks);
      }
  
      private void copy(int[][] toBlocks, int[][] fromBlocks) {
         for (int row = 0; row < n; row++)
             for (int col = 0; col < n; col++)
                 toBlocks[row][col] = fromBlocks[row][col];
      }
	
	public int hamming() {
		int ham = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if ((i * n + j + 1) != blocks[i][j] && blocks[i][j] != 0) {    //!=0 means this is not a blank
					ham++;
				}
			}
		}

		return ham;

	}

	public int manhattan() {
		int man = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int ri = (blocks[i][j] - 1) / n;  //right row number
		        int rj = (blocks[i][j] - 1) % n;  //right col number
				
				if (blocks[i][j] != 0 ) {
					int mani = Math.abs(ri - i);
					int manj = Math.abs(rj - j);
					man = mani + manj + man;
				}
			}
		}

		return man;
		
	}

	public boolean isGoal() {
		return hamming() == 0;
	}

	public Board twin() {

		Board twin = new Board(blocks);
		 /*
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				twin[i][j] = blocks[i][j];
			}
		}
		*/
		if(blocks[0][0] == 0 || blocks[0][1] == 0) {
			twin.swap(1, 0, 1, 1);
		} else {
			twin.swap(0, 0, 0, 1);
		}

		return twin;
	}

	private void swap(int i, int j, int p, int q) {
		int k = blocks[i][j];
		blocks[i][j] = blocks[p][q];
		blocks[p][q] = k;
	} 

	public boolean equals(Object y) {
		
		if (y == null) {
			return false;
		}

		if (y == this) {
			return true;
		}

		if (y.getClass() != this.getClass()) {
			return false;
		}

		Board that = (Board) y;

		if (that.n != this.n) {
			return false;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (that.blocks[i][j] != blocks[i][j]) {
					return false;
				}
			}
		}

		return true;

	}

	public Iterable<Board> neighbors() {
		ArrayList<Board> neighbors = new ArrayList<Board>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (blocks[i][j] == 0) {
					if (i > 0) {    //exchange with item up
						Board neighborUp = new Board(blocks);
						neighborUp.swap(i, j, i - 1, j);
						neighbors.add(neighborUp);
					}

					if (i < n - 1) { //exchange with item down
						Board neighborDown = new Board(blocks);
						neighborDown.swap(i, j, i + 1, j);
						neighbors.add(neighborDown);
					}

					if (j > 0) {  //exchange with item left
						Board neighborLeft = new Board(blocks);
						neighborLeft.swap(i, j, i, j - 1);
						neighbors.add(neighborLeft);
					}

					if (j < n - 1) {  //exchange with item right
						Board neighborRight = new Board(blocks);
						neighborRight.swap(i, j, i, j + 1);
						neighbors.add(neighborRight);
					}
				}
			}
		}

		return neighbors;
	}

	public String toString() {    //change the puzzle into string type
		StringBuilder StringB = new StringBuilder();
		StringB.append(n + "\n"); // first add the dimension of the puzzle
		for (int i = 0; i < n; i++ ) {
			for (int j = 0; j < n; j++ ) {
				StringB.append(String.format("%2d ", blocks[i][j]));  //then add every item col respectively
			}
			
			StringB.append("\n");  //to next row
		}
		return StringB.toString();

	}


	public static void main(String[] args) {
		
	}


}