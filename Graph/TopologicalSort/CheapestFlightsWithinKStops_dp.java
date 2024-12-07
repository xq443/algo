import java.util.Arrays;

public class CheapestFlightsWithinKStops_dp {
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    int[][] dp = new int[n + 1][k + 2];
    for(int i = 0; i < n; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
    }
    dp[src][0] = 0;
    int ret = Integer.MAX_VALUE / 2;

    for(int j = 1; j <= k + 1; j++) {
      for(int[] flight : flights) {
        int a = flight[0], b = flight[1], p = flight[2];
        // update dp table
        dp[b][j] = Math.min(dp[b][j], dp[a][j - 1] + p);
        // If we reach the destination, update the result
        if(b == dst) {
          ret = Math.min(ret, dp[b][j]);
        }
      }
    }

    return (ret == Integer.MAX_VALUE / 2) ? -1 : ret;
  }

  public static void main(String[] args) {
    CheapestFlightsWithinKStops_dp c =  new CheapestFlightsWithinKStops_dp();
    int n2 = 4;
    int[][] flights2 = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
    int src2 = 0;
    int dst2 = 3;
    int k2 = 1;
    System.out.println("Test Case 2 - Cheapest Price: " + c.findCheapestPrice(n2, flights2, src2, dst2, k2));  // Expected output: 700
  }

}
