package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    double[] fractions;
    int numOfExp;
    int size;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N<=0 || T<= 0) {
            throw new IllegalArgumentException("N and T can't be <= 0");
        }

        numOfExp = T;
        size = N;
        fractions = new double[T];

        for (int i = 0; i < T; i +=1) {
            Percolation p = pf.make(N);
            double fraction = 0;
            while (!p.percolates()) {
                int row = StdRandom.uniform(0,N);
                int col = StdRandom.uniform(0,N);
                // open random site
                p.open(row,col);
                fraction = (double)p.numberOfOpenSites()/(double)(N*N);
            }
            fractions[i] = fraction;
        }
    }
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {

        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return (mean()-1.96*stddev()/Math.sqrt(size));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return (mean()+1.96*stddev()/Math.sqrt(size));
    }

    public static void main(String[] args) {
        System.out.println("Some percolation stats: ");
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(20, 100,pf);
        System.out.println("Percolation threshold: "+ps.mean());
        System.out.println("Percolation stddev: "+ps.stddev());
        System.out.println("Percolation confidence high: "+ps.confidenceHigh());
        System.out.println("Percolation confidence low: "+ps.confidenceLow());
    }
}
