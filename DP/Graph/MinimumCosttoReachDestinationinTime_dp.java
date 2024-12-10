package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumCosttoReachDestinationinTime_dp {
  public int minCost(int maxTime, int[][] edges, int[] passingFees) {
    int n = passingFees.length;
    // build up graph
    List<List<int[]>> graph = new ArrayList<>();
    for(int e : passingFees) {
      graph.add(new ArrayList<>());
    }
    for(int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      int time = edge[2];
      graph.get(u).add(new int[]{v, time});
      graph.get(v).add(new int[]{u, time});
    }

    // initialize the dp table: at city n, with max time bounded, the min cost
    int[][] dp = new int[n][maxTime + 1];
    for(int i = 0; i < n; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
    }
    dp[0][0] = passingFees[0];
    for(int t = 0; t <= maxTime; t++) {
      for(int city = 0; city < n; city++) {
        if(dp[city][t] == Integer.MAX_VALUE / 2) continue;
        // explore neighbours
        for(int[] neighbor : graph.get(city)) {
          int next = neighbor[0];
          int time = neighbor[1];
          int newTime = t + time;

          if(newTime <= maxTime) {
            dp[next][newTime] = Math.min(dp[next][newTime], dp[city][t] + passingFees[next]);
          }
        }
      }
    }
    int min = Integer.MAX_VALUE / 2;
    for(int t = 0; t <= maxTime; t++) {
      min = Math.min(min, dp[n - 1][t]);
    }
    return min == Integer.MAX_VALUE / 2 ? -1 : min;
  }

  public static void main(String[] args) {
    MinimumCosttoReachDestinationinTime_dp mc = new MinimumCosttoReachDestinationinTime_dp();
    int maxTime = 30;
    int[][] edges = {
        {0, 1, 10},
        {1, 2, 10},
        {2, 5, 10},
        {0, 3, 1},
        {3, 4, 10},
        {4, 5, 15}
    };
    int[] passingFees = {5, 1, 2, 20, 20, 3};
    System.out.println(mc.minCost(maxTime, edges, passingFees));
  }
}
