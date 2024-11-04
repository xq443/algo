package Cost;

import java.util.*;

public class LowestCost {
  public static class Edge {
    String dest;
    int cost;

    public Edge(String dest, int cost) {
      this.dest = dest;
      this.cost = cost;
    }
  }

  Map<String, List<Edge>> map = new HashMap<>();

  public void buildMap(String input) {
    if (input == null || input.isEmpty()) {
      return; // Early exit for empty input
    }

    String[] groups = input.split(",");
    for (String group : groups) {
      String[] response = group.split(":");
      String src = response[0];
      String dest = response[1];
      int cost = Integer.parseInt(response[3]);

      map.computeIfAbsent(src, k -> new ArrayList<>()).add(new Edge(dest, cost));
      map.computeIfAbsent(dest, k -> new ArrayList<>()).add(new Edge(src, cost));
    }
  }

  public int lowestCost(String input, String output) {
    buildMap(input);
    String src = output.split(":")[0];
    String dest = output.split(":")[1];

    PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
    queue.offer(new Edge(src, 0));
    Map<String, Integer> minCostMap = new HashMap<>(); // track min cost to each node
    minCostMap.put(src, 0);
    Map<String, String> parent = new HashMap<>(); // Track the path
    Set<String> visited = new HashSet<>();

    while (!queue.isEmpty()) {
      Edge edge = queue.poll();
      String curr = edge.dest;
      int currCost = edge.cost;

      if (visited.contains(curr)) continue;
      visited.add(curr);

      if (curr.equals(dest)) {
        printPath(parent, src, dest);
        return currCost;
      }

      if (map.containsKey(curr)) {
        for (Edge e : map.get(curr)) {
          int newCost = currCost + e.cost;
          if (!minCostMap.containsKey(e.dest) || newCost < minCostMap.get(e.dest)) {
            minCostMap.put(e.dest, newCost);
            queue.offer(new Edge(e.dest, newCost));
            parent.put(e.dest, curr); // Update the path tracking
          }
        }
      }
    }

    System.out.println("No path found from " + src + " to " + dest);
    return -1; // Indicates no path found
  }

  private void printPath(Map<String, String> parent, String start, String end) {
    List<String> path = new ArrayList<>();
    for (String at = end; at != null; at = parent.get(at)) {
      path.add(at);
    }
    Collections.reverse(path); // Reverse to get correct order
    System.out.println("Path: " + String.join("->", path));
  }

  public static void main(String[] args) {
    LowestCost lc = new LowestCost();
    String input = "US:CA:Car:300,CA:MX:Bus:200,MX:BR:Flight:800";
    String output = "US:BR";
    System.out.println("Lowest cost: " + lc.lowestCost(input, output));
  }
}
