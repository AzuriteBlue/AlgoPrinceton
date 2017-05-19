import edu.princeton.cs.algs4.StdRandom;


public class PercolationStats {

    private double[] results;

    public PercolationStats(int n, int trials) {
        // perform trials independent experiments on an n-by-n grid

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        results = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(1, n+1), StdRandom.uniform(1, n+1));
            }
            results[i] = (double) percolation.numberOfOpenSites()/(n*n);
        }

    }
    public double mean() {
        // sample mean of percolation threshold
        return edu.princeton.cs.algs4.StdStats.mean(results);
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        return edu.princeton.cs.algs4.StdStats.stddev(results);
    }

    public double confidenceLo() {
        // low  endpoint of 95% confidence interval
        double denom = Math.sqrt(results.length);
        double mean = mean();
        double stddev = stddev();
        return mean - 1.96 * stddev / denom;
    }

    public double confidenceHi() {
        // high endpoint of 95% confidence interval
        double denom = Math.sqrt(results.length);
        double mean = mean();
        double stddev = stddev();
        return mean + 1.96 * stddev / denom;
    }

    public static void main(String[] args) {
        // test client (described below)
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats pStats = new PercolationStats(n, t);
        System.out.printf("mean                    = %f\n", pStats.mean());
        System.out.printf("stddev                  = %f\n", pStats.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]\n", pStats.confidenceLo(), pStats.confidenceHi());
    }
}