package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumCosttoReachDestinationinTime_dijkstra {
   static class Pair{
     int node, cost, time;

     public Pair(int node, int cost, int time) {
       this.node = node;
       this.cost = cost;
       this.time = time;
     }
   }

  public int minCost(int maxTime, int[][] edges, int[] passingFees) {
    int n = passingFees.length;
    // build up graph
    List<List<int[]>> graph = new ArrayList<>();
    for (int e : passingFees) {
      graph.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      int time = edge[2];
      graph.get(u).add(new int[]{v, time});
      graph.get(v).add(new int[]{u, time});
    }

    // To store the minimum cost for each node at a given time
    int[][] minCost = new int[n][maxTime + 1];
    for (int[] row : minCost) {
      for (int j = 0; j <= maxTime; j++) {
        row[j] = Integer.MAX_VALUE; // Initialize with a large value
      }
    }
    minCost[0][0] = passingFees[0];
    PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->a.cost - b.cost);
    pq.add(new Pair(0, passingFees[0],0)); // // [city, cost, time]

    while (!pq.isEmpty()) {
      Pair curr = pq.poll();
      int node = curr.node, cost = curr.cost, time = curr.time;
      if(node == n - 1) return cost;

      for(int[] next: graph.get(curr.node)) {
        int nextNode = next[0], nextTime = next[1];
        int newTime = time + nextTime;
        if(newTime <= maxTime) {
          int newCost = cost + passingFees[nextNode];
          if (newCost < minCost[nextNode][newTime]) {
            minCost[nextNode][newTime] = newCost;
            pq.add(new Pair(nextNode, newCost, newTime));
          }
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    MinimumCosttoReachDestinationinTime_dijkstra mc = new MinimumCosttoReachDestinationinTime_dijkstra();
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
