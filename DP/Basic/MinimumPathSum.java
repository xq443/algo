package Basic;

public class MinimumPathSum {

  /**
   * Given a m x n grid filled with non-negative numbers,
   * find a path from top left to bottom right,
   * which minimizes the sum of all numbers along its path.
   * Note: You can only move either down or right at any point in time.
   * @param grid int[][]
   * @return int
   */
  public int minPathSum(int[][] grid) {
    if(grid == null || grid.length == 0) return 0;
    int m = grid.length, n = grid[0].length;
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        // initialize the sum of path to [0,0] is grid[i][j]
        if(i == 0 && j == 0) dp[i][j] = grid[i][j];
        else if(i == 0) {
          // if now is in the i = 0, then it can only be reached from the same row
          dp[i][j] = dp[i][j - 1] + grid[i][j];
        } else if (j == 0) {
          // if now is in the j = 0, then it can only be reached from the same col
          dp[i][j] = dp[i - 1][j] + grid[i][j];
        } else {
          // can be reached from the up or left point, but need to pick the min prev sum
          dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        }
      }
    }
    return dp[m - 1][n - 1];
  }

  public static void main(String[] args) {
    MinimumPathSum minimumPathSum = new MinimumPathSum();
    int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
    System.out.println(minimumPathSum.minPathSum(grid));
  }
}
