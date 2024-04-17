package Basic;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumEarningsFromTaxi {

  /**
   * There are n points on a road you are driving your taxi on. You cannot change the direction of the taxi.
   *
   * The passengers are represented by a 0-indexed 2D integer array rides,
   * where rides[i] = [starti, endi, tipi] denotes the ith passenger requesting a ride
   * from point starti to point endi who is willing to give a tipi dollar tip.
   * For each passenger i you pick up, you earn endi - starti + tipi dollars.
   * You may only drive at most one passenger at a time.
   *
   * Given n and rides, return the maximum number of dollars you can earn by picking up the passengers optimally.
   * Note: You may drop off a passenger and pick up a different passenger at the same point.
   * @param n
   * @param rides
   * @return
   */
  public long maxTaxiEarnings(int n, int[][] rides) {
    long prev = 0, ret = 0;
    int idx = 0;

    // store the maximum profit earned before each point on the road
    long[] dp = new long[n + 1];

    // sort by the starting time
    Arrays.sort(rides, Comparator.comparingInt(a -> a[0]));
    for (int i = 0; i < rides.length; i++) {
      // get rides detail
      int start = rides[i][0];
      int end = rides[i][1];
      int tips = rides[i][2];

      while(idx <= start) { // can be overlapped at the start and end time
        // Update prev with the maximum profit earned before the current ride's start point
        prev = Math.max(prev, dp[idx]);
        idx++;
      }
      // Calculate profit for the current ride
      long profit = end - start + tips;

      // Update dp array with the maximum profit earned before the current ride's end point
      dp[end] = Math.max(dp[end], prev + profit);

      // Update overall maximum earnings
      ret = Math.max(ret, dp[end]);
    }
    return ret;
  }

  public static void main(String[] args) {
    MaximumEarningsFromTaxi maximumEarningsFromTaxi = new MaximumEarningsFromTaxi();
    int n = 5;
    int[][] rides = {{2,5,4},{1,5,1}};
    System.out.println(maximumEarningsFromTaxi.maxTaxiEarnings(n, rides));
  }
}
