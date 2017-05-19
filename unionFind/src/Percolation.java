import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private WeightedQuickUnionUF uf;
    private boolean[] openStates;
    private int n;

    public Percolation(int n) {
        // create n-by-n grid, with all sites blocked
        if (n <= 0) throw new java.lang.IllegalArgumentException();
        this.n = n;
        uf = new WeightedQuickUnionUF(n*n+2);
        openStates = new boolean[n*n+2];
    }

    private void checkBound(int row, int col) {
        if ((row <= 0 || col >= n+1) || (col <= 0 || col >= n+1)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    public void open(int row, int col) {
        // open site (row, col) if it is not open already
        checkBound(row, col);

        int curIndex = n*(row-1)+col;
        if (!isOpen(row, col)) {
            openStates[curIndex] = true;

            if (row == 1 && row == n) {
                uf.union(0, 1);
                uf.union(1, 2);
            } else if (row == 1) {
                uf.union(curIndex, 0);
                if (isOpen(row+1, col)) {
                    uf.union(curIndex, n*row+col);
                }
            } else if (row == n) {
                uf.union(curIndex, n*n+1);
                if (isOpen(row-1, col)) {
                    uf.union(curIndex, n*(row-2)+col);
                }
            } else {
                if (isOpen(row - 1, col)) {
                    uf.union(curIndex, n * (row - 2) + col);
                }
                if (isOpen(row + 1, col)) {
                    uf.union(curIndex, n * row + col);
                }
            }

            if (col != 1 && col != n) {
                if (isOpen(row, col-1)) {
                    uf.union(curIndex, n*(row-1)+col-1);
                }
                if (isOpen(row, col+1)) {
                    uf.union(curIndex, n*(row-1)+col+1);
                }
            } else if (col == 1) {
                if (isOpen(row, col+1)) {
                    uf.union(curIndex, n*(row-1)+col+1);
                }
            } else {
                if (isOpen(row, col - 1)) {
                    uf.union(curIndex, n * (row - 1) + col - 1);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        // is site (row, col) open?
        checkBound(row, col);
        int curIndex = n*(row-1)+col;
        return openStates[curIndex];
    }

    public boolean isFull(int row, int col) {
        // is site (row, col) full?
        checkBound(row, col);
        int curIndex = n*(row-1)+col;
        return isOpen(row, col) && uf.connected(0, curIndex);
    }

    public int numberOfOpenSites() {
        // number of open sites
        int count = 0;
        for (boolean flag : openStates) {
            if (flag) {
                count++;
            }
        }
        return count;
    }

    public boolean percolates() {
        // does the system percolate?
        return uf.connected(0, n*n+1);
    }

    public static void main(String[] args) {
        // test client (optional)

    }
}