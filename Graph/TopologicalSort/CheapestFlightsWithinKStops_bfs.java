import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheapestFlightsWithinKStops_bfs {
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    List<List<int[]>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int[] flight : flights) {
      int from = flight[0], to = flight[1], price = flight[2];
      graph.get(from).add(new int[]{to, price});
    }
    int[] memo = new int [n];
    Arrays.fill(memo, Integer.MAX_VALUE / 2);

    int ret = Integer.MAX_VALUE / 2;

    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{src, 0});

    int stops = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for(int i = 0; i < size; i++) {
        int[] curr = queue.poll();
        int node = curr[0];
        int cost = curr[1];
        if(cost > memo[node]) continue;
        memo[node] = cost;

        if(node == dst) ret = Math.min(ret, cost);

        // neighbours
        for(int[] neighbor : graph.get(node)) {
          int neighborTo = neighbor[0], neighborCost = neighbor[1];
          if(neighborCost + cost > memo[neighborTo]) continue;
          queue.offer(new int[]{neighborTo, neighborCost + cost});
        }
      }
      if(stops++ > k) break;
    }
    return (ret == Integer.MAX_VALUE / 2) ? -1 : ret;
  }

  public static void main(String[] args) {
    CheapestFlightsWithinKStops_bfs c =  new CheapestFlightsWithinKStops_bfs();
    int n2 = 4;
    int[][] flights2 = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
    int src2 = 0;
    int dst2 = 3;
    int k2 = 1;
    System.out.println("Test Case 2 - Cheapest Price: " + c.findCheapestPrice(n2, flights2, src2, dst2, k2));  // Expected output: 700
  }
}
