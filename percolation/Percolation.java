import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int virtualTopSiteIndex;
    private final int virtualBottomSiteIndex;
    private final int N;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean[] sites;
    private int openSitesCount = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = n;
        this.virtualTopSiteIndex = N * N;
        this.virtualBottomSiteIndex = N * N + 1;
        this.sites = new boolean[N * N];
        for (int i = 0; i < N * N; i++) {
            sites[i] = false;
        }

        //includes virtual top and bottom site
        this.weightedQuickUnionUF = new WeightedQuickUnionUF((N * N) + 2);
    }

    private boolean isIndexInvalid(int row, int col) {
        if (row <= 0 || col <= 0 || row > N || col > N) {
            return true;
        }
        else return false;
    }

    private int xyTo1D(int row, int col) {
        if (isIndexInvalid(row, col)) {
            throw new IllegalArgumentException();
        }
        int index = (row - 1) * N + (col - 1);
        return index;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isIndexInvalid(row, col)) {
            throw new IllegalArgumentException();
        }
        int index = xyTo1D(row, col);

        if (sites[index]) {
            return;
        }
        sites[index] = true;
        openSitesCount++;
        if (row == 1) {
            weightedQuickUnionUF.union(index, virtualTopSiteIndex);
        }
        if (row > 1) {
            int upIndex = xyTo1D(row - 1, col);
            if (sites[upIndex]) {
                weightedQuickUnionUF.union(index, upIndex);
            }
        }
        if (row < N) {
            int downIndex = xyTo1D(row + 1, col);
            if (sites[downIndex]) {
                weightedQuickUnionUF.union(index, downIndex);
            }
        }
        if (row == N) {
            weightedQuickUnionUF.union(index, virtualBottomSiteIndex);
        }
        if (col > 1) {
            int leftIndex = xyTo1D(row, col - 1);
            if (sites[leftIndex]) {
                weightedQuickUnionUF.union(index, leftIndex);
            }
        }
        if (col < N) {
            int rightIndex = xyTo1D(row, col + 1);
            if (sites[rightIndex]) {
                weightedQuickUnionUF.union(index, rightIndex);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (isIndexInvalid(row, col)) {
            throw new IllegalArgumentException();
        }
        int index = xyTo1D(row, col);
        return sites[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (isIndexInvalid(row, col)) {
            throw new IllegalArgumentException();
        }
        int index = xyTo1D(row, col);
        boolean isSameComponent = weightedQuickUnionUF.find(index) == weightedQuickUnionUF.find(virtualTopSiteIndex);
        return isSameComponent;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {
        boolean isSameComponent = weightedQuickUnionUF.find(virtualBottomSiteIndex)
                == weightedQuickUnionUF.find(virtualTopSiteIndex);
        return isSameComponent;
    }

    // test client (optional)
    public static void main(String[] args) {
        //implement later
    }
}
