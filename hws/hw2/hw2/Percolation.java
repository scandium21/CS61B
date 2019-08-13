package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    boolean[][] grid;
    int size;
    int siteOpen;
    WeightedQuickUnionUF connectedSites;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N)      {
        grid = new boolean[N][N];
        size = N;
        siteOpen = 0;
        // two extra sites for main top and bottom connector
        connectedSites = new WeightedQuickUnionUF(N*N + 2);
        // set all sites to blocked
        for(int i = 0; i < N; i+=1) {
            for (int j =0; j < N; j+=1) {
                grid[i][j] = false;
            }
        }
    }

    // transform (row, col) coordinate to site index
    private int xyTo1D (int row, int col) {
        return row * (size) + col;
    }

    // connect to open neighboring sites
    private void connectNeighbors (int row, int col, int siteIndex) {
        if (row -1 >=0 && grid[row - 1][col]) {
            connectedSites.union(siteIndex, xyTo1D(row-1, col));
        }
        if (row + 1 < size && grid[row + 1][col]) {
            connectedSites.union(siteIndex, xyTo1D(row+1, col));
        }
        if (col - 1 >= 0 && grid[row][col - 1]) {
            connectedSites.union(siteIndex, xyTo1D(row, col-1));
        }
        if (col + 1 < size && grid[row][col + 1]) {
            connectedSites.union(siteIndex, xyTo1D(row, col+1));
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!grid[row][col]) {
            siteOpen += 1;
        }
        grid[row][col] = true;
        int siteIndex = xyTo1D(row, col);
        connectNeighbors(row, col, siteIndex);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int siteIndex = xyTo1D(row, col);

        if (connectedSites.connected(size*size,siteIndex)) {
            return true;
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites()            {
        return siteOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        // connect all open top/bottom sites to virtue top/bottom site
        for (int i = 0; i < size; i+=1) {
            if (grid[0][i]) {
                connectedSites.union(i, size*size);
            }
            if (grid[size-1][i] && !connectedSites.connected(size*size, size*size+1)) {
                connectedSites.union(size*(size-1)+i,size*size+1);
            }
        }
        if (connectedSites.connected(size*size, size*size+1)) {
            return true;
        }
        return false;
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {

    }

}
