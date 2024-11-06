package Stripe.Cost;

import java.util.*;

public class LowestCost {
  public static class Edge {
    String node;
    int cost;

    public Edge(String dest, int cost) {
      this.node = dest;
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

  public int lowestCost(String input, String src, String dest, String method) throws Exception {
    buildMap(input);

    // min heap to sort the queue by cost in asc order
    PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
    queue.offer(new Edge(src, 0));

    Map<String, Integer> minCosts = new HashMap<>(); // map: Track minimum costs for each place(node)
    minCosts.put(src, 0);

    Map<String, String> parent = new HashMap<>(); // Track the path
    Set<String> visited = new HashSet<>();

    while (!queue.isEmpty()) {
      Edge edge = queue.poll();
      String curr = edge.node;
      int currCost = edge.cost;

      if (visited.contains(curr)) continue;
      visited.add(curr);

      // Check if we have reached the destination
      if (curr.equals(dest)) {
        printPath(parent, src, dest);
        return currCost;
      }

      if (map.containsKey(curr)) {
        for (Edge neighbor : map.get(curr)) {
          int newCost = currCost + neighbor.cost;

          // Only consider this path if it’s the lowest cost path to the neighbor so far
          if (newCost < minCosts.getOrDefault(neighbor.node, Integer.MAX_VALUE)) {
            minCosts.put(neighbor.node, newCost);
            queue.offer(new Edge(neighbor.node, newCost));
            parent.put(neighbor.node, curr); // Track path for later reconstruction
          }
        }
      }
    }
    // If we exit the loop without having found the destination, throw exception
    throw new Exception("No path found from " + src + " to " + dest);
  }

  private void printPath(Map<String, String> parent, String start, String end) {
    List<String> path = new ArrayList<>();
    for (String at = end; at != null; at = parent.get(at)) {
      path.add(at);
    }
    Collections.reverse(path); // Reverse to get the correct order
    System.out.println("Path: " + String.join("->", path));
  }

  public static void main(String[] args) throws Exception {
    try {
      LowestCost lc = new LowestCost();
      String input = "US:CA:Car:300,CA:MX:Bus:200,MX:BR:Flight:800,US:BR:KJ:2";
      String src = "US";
      String dest = "BR";
      String method = "Car";
      System.out.println("Lowest cost: " + lc.lowestCost(input, src, dest, method));
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
