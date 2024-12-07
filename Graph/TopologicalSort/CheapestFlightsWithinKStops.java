import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class CheapestFlightsWithinKStops {
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    // adj list
    List<List<int[]>> next = new ArrayList<>();
    for(int i = 0; i < n; i++) {
      next.add(new ArrayList<>());
    }
    for(int[] flight : flights) {
      int start = flight[0];
      int end = flight[1];
      int cost = flight[2];
      next.get(start).add(new int[] {end, cost});
    }

    // Cost matrix gives the minimum cost to reach city i using exactly j stops.
    int[][] cost = new int[n][k + 2];
    for(int i = 0; i < n; i++) {
      Arrays.fill(cost[i], Integer.MAX_VALUE / 2);
    }

    // Priority queue for Dijkstra-like traversal
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    pq.offer(new int[] {0, src, 0}); // {cost, current_city, stops}

    while(!pq.isEmpty()) {
      int[] current = pq.poll();
      int c = current[0];
      int curr = current[1];
      int stops = current[2];

      if(curr == dst) return c;
      if(stops > k || cost[curr][stops] < Integer.MAX_VALUE / 2) continue;

      // update the cost matrix
      cost[curr][stops] = c;

      // explore neighbours
      for(int[] ticket : next.get(curr)) {
        int nxt = ticket[0];
        int price = ticket[1];
        if(cost[nxt][stops + 1] < Integer.MAX_VALUE / 2) continue;
        pq.offer(new int[] {c + price, nxt, stops + 1});
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    CheapestFlightsWithinKStops c =  new CheapestFlightsWithinKStops();

    int n2 = 4;
    int[][] flights2 = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
    int src2 = 0;
    int dst2 = 3;
    int k2 = 1;
    System.out.println("Test Case 2 - Cheapest Price: " + c.findCheapestPrice(n2, flights2, src2, dst2, k2));  // Expected output: 700
  }
}
