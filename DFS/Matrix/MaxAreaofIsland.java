public class MaxAreaofIsland {
  public int maxAreaOfIsland(int[][] grid) {
    int ret = 0;
    if(grid == null || grid.length == 0) return 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if(grid[i][j] == 1) {
          ret = Math.max(ret, maxAreaOfIsland_dfs(ret, grid, i, j));
        }
      }
    }
    return ret;
  }
  private int maxAreaOfIsland_dfs(int ret, int[][] grid, int i, int j) {
    if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
      return 0; // accumulative value
    }

    int delta = 1;
    grid[i][j] = 0; // mark as visited

    delta += maxAreaOfIsland_dfs(ret, grid, i + 1, j);
    delta += maxAreaOfIsland_dfs(ret, grid, i - 1, j);
    delta += maxAreaOfIsland_dfs(ret, grid, i, j + 1);
    delta += maxAreaOfIsland_dfs(ret, grid, i, j - 1);
    return delta;
  }

  public static void main(String[] args) {
    MaxAreaofIsland maxAreaofIsland = new MaxAreaofIsland();
    int[][] grid = {
        {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
        {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
        {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
    };
    System.out.println(maxAreaofIsland.maxAreaOfIsland(grid));
  }
}
