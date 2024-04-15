package Basic;

public class UniquePath {

  /**
   * There is a robot on an m x n grid.
   * The robot can only move either down or right at any point in time.
   *
   * Given the two integers m and n,
   * return the number of possible unique paths that the robot can take to reach the bottom-right corner.
   * The test cases are generated so that the answer will be less than or equal to 2 * 109.
   * @param m int
   * @param n int
   * @return int
   */
  public int uniquePaths(int m, int n) {
    if(m == 0 && n == 0) return 0;
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        // initialize the num of path to [0,0] is 1
        if(i == 0 && j == 0) dp[i][j] = 1;
        else if(i == 0) {
          // if now is in the i = 0, then it can only be reached from the same row
          dp[i][j] = dp[i][j - 1];
        } else if (j == 0) {
          // if now is in the j = 0, then it can only be reached from the same col
          dp[i][j] = dp[i - 1][j];
        } else {
          // can be reached from the up or left point
          dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
      }
    }
    return dp[m - 1][n - 1];
  }

  public static void main(String[] args) {
    UniquePath uniquePath = new UniquePath();
    int m = 3, n = 7;
    System.out.println(uniquePath.uniquePaths(m,n));
  }
}
