import java.lang.Math;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
	private double count;
	private double grid;
	private double times;
	private double[] opensites;

	public PercolationStats(int n, int trials) {
		grid = n;
		times = trials;
		opensites = new double[(int)times + 1];

		if ( grid <= 0 || times <= 0){
			throw new IndexOutOfBoundsException("Invalide Input");
		}

		for (int i = 1; i <= times; i++) {
			count = 0;
			Percolation perc = new Percolation((int)grid);

			while (!perc.percolates()) {
				int p = StdRandom.uniform(1, (int)grid + 1);
				int q = StdRandom.uniform(1, (int)grid + 1);
				perc.open(p, q);
				count++;
			}

			opensites[i] = count / (grid * grid);
		}
	}

	public double mean() {
		double mean = StdStats.mean(opensites, 1, (int)times + 1);
		return mean;
	}

	public double stddev() {
		double stddev = StdStats.stddev(opensites, 1, (int)times + 1);
		return stddev;
	}

	public double confidenceLo() {
		double k = Math.sqrt(times);
		double temp = (1.96 * stddev()) / k;
		double Lo = mean() - temp;
		return Lo;
	}


	public double confidenceHi() {
		double k = Math.sqrt(times);
		double temp = (1.96 * stddev()) / k;
		double Hi = mean() + temp;
		return Hi;
	}


	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);

		PercolationStats percsimulate = new PercolationStats(n, trials);
		

		StdOut.printf("mean = %.6f\n", percsimulate.mean());
		StdOut.printf("stddev = %.17f\n", percsimulate.stddev());
		StdOut.printf("95%% confidence interval = [%.16f, %.16f]", percsimulate.confidenceLo(), percsimulate.confidenceHi());
	}
}