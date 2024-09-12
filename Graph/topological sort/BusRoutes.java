import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BusRoutes {
  public int numBusesToDestination(int[][] routes, int source, int target) {
    if(source == target) return 0;

    // build up graph: stop -> bus routes
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for(int i = 0; i < routes.length; i++) {
      for(int stop : routes[i]) {
        graph.computeIfAbsent(stop, k -> new ArrayList<>()).add(i);
      }
    }
    Queue<Integer> queue = new LinkedList<>();
    int bus = 0;
    boolean[] visited = new boolean[routes.length];
    queue.add(source);

    while(!queue.isEmpty()) {
      int len = queue.size();
      bus++;
      while(len-- > 0) {
        Integer curr = queue.poll();  // Use Integer to handle potential null values
        if(curr == null) continue;    // Safely skip null values

        if(graph.containsKey(curr)) {
          for(int next : graph.get(curr)) {
            if(visited[next]) continue;
            visited[next] = true;
            for(int stop : routes[next]) {
              if(stop == target) return bus;
              queue.add(stop);
            }
          }
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    BusRoutes busRoutes = new BusRoutes();
    int[][] routes = {{7,12},{4,5,15},{6},{15,19},{9,12,13}};
    int source = 15, target = 12;
    System.out.println(busRoutes.numBusesToDestination(routes, source, target));

    int[][] routes2 = {{1,2,7},{3,6,7}};
    int source2 = 1, target2 = 6;
    System.out.println(busRoutes.numBusesToDestination(routes2, source2, target2));
  }
}
