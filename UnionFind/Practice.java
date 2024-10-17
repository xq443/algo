import java.util.Arrays;

public class Practice {
  public DSU uf;
  public int rows;
  public int cols;
  public int[] prevParent;
  public int[] rank;

  public void initialize(int row, int col) {
    this.uf = new DSU(row * col);
    this.prevParent = new int[col];
    Arrays.fill(prevParent, -1);
    this.cols = col;
    this.rows = row;
    this.rank = new int[row * col];
  }

  public int countIsland(char[] grid, int rowIdx) {
    int[] currParent = new int[cols];
    Arrays.fill(currParent, -1);

    for(int i = 0; i < grid.length; i++) {
      if(grid[i] == '1') {
        int currIdx = i + rowIdx * cols;
        uf.addNew(currIdx);
        currParent[i] = currIdx;

        if (i > 0 && grid[i - 1] == '1') {
          uf.union(currIdx,  rowIdx * cols + (i - 1));
        }

        if(prevParent[i] != -1) {
          uf.union(currIdx, prevParent[i]);
        }
      }
    }
    prevParent = currParent;
    return uf.getCount();
  }

  public static void main(String[] args) {
    Practice numberofIslands = new Practice();
    char[][] grid = {
        {'1', '1', '0', '1', '0'}
    };

    numberofIslands.initialize(grid.length, grid[0].length);
    System.out.println("Number of islands "  + numberofIslands.countIsland(grid[grid.length - 1], grid.length - 1));
  }
}
