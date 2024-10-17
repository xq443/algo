import java.util.Arrays;

public class NumberofIslands {

  private UnionFind uf;
  private int n; // Number of columns (assumed constant for all rows)
  private int[] prevRowParent; // Parent info for the previous row

  // Method to initialize the Union-Find and previous row tracking
  public void initialize(int rows, int columns) {
    this.n = columns;
    this.uf = new UnionFind(rows * columns); // Union-Find for the entire grid (m * n)
    this.prevRowParent = new int[columns];
    Arrays.fill(prevRowParent, -1); // Initialize with no land
  }

  // Method to process one row of the grid at a time
  public int numIslands(char[] oneRowOfGrid, int rowIndex) {
    int[] currentRowParent = new int[n];
    Arrays.fill(currentRowParent, -1); // Start fresh for the current row

    for (int j = 0; j < oneRowOfGrid.length; j++) {
      if (oneRowOfGrid[j] == '1') {
        // Convert the 2D index (rowIndex, j) into 1D index
        int currentIndex = rowIndex * n + j;
        uf.addNewSet(currentIndex);
        currentRowParent[j] = currentIndex;

        // Check left connection (within the same row)
        if (j > 0 && oneRowOfGrid[j - 1] == '1') {
          uf.union(currentIndex, currentIndex - 1);
        }

        // Check top connection (with the previous row)
        if (prevRowParent[j] != -1) {
          uf.union(currentIndex, prevRowParent[j]);
        }
      }
    }

    // Move current row's parent array to previous row parent array
    prevRowParent = currentRowParent;

    return uf.getCount();
  }

  class UnionFind {
    private int[] parent;
    private int[] rank;
    private int count; // Track the number of disjoint sets (islands)

    public UnionFind(int size) {
      parent = new int[size];
      rank = new int[size];
      count = 0; // Initially, there are no islands
      Arrays.fill(parent, -1); // Use -1 to indicate uninitialized cells
    }

    public int find(int x) {
      if (parent[x] != x) {
        parent[x] = find(parent[x]); // Path compression
      }
      return parent[x];
    }

    public void union(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);
      if (rootX != rootY) {
        if (rank[rootX] > rank[rootY]) {
          parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
          parent[rootX] = rootY;
        } else {
          parent[rootY] = rootX;
          rank[rootX]++;
        }
        count--; // Merged two sets
      }
    }

    public void addNewSet(int x) {
      if (parent[x] == -1) { // If this is a new land cell
        parent[x] = x;
        count++; // New island
      }
    }

    public int getCount() {
      return count;
    }
  }

  public static void main(String[] args) {
    NumberofIslands numberofIslands = new NumberofIslands();
    char[][] grid = {
        {'1', '1', '1', '1', '0'},
        {'1', '1', '0', '1', '0'},
        {'1', '1', '0', '0', '1'},
        {'0', '0', '1', '0', '0'}
    };

    numberofIslands.initialize(grid.length, grid[0].length);

    for (int i = 0; i < grid.length; i++) {
      System.out.println("Number of islands after processing row " + i + ": " + numberofIslands.numIslands(grid[i], i));
    }
    System.out.println("Number of islands "  + numberofIslands.numIslands(grid[grid.length - 1], grid.length - 1));
  }
}
