package Stripe.Cost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultipleIntermediates {
  public static class Edge {
    String dest;
    String method;
    int cost;

    public Edge(String dest, String method, int cost) {
      this.dest = dest;
      this.method = method;
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
      String method = response[2];
      int cost = Integer.parseInt(response[3]);
      map.computeIfAbsent(src, k -> new ArrayList<>()).add(new Edge(dest, method, cost));
      //map.computeIfAbsent(dest, k -> new ArrayList<>()).add(new Edge(src, method, cost));
    }
  }

  public String CostMultiIntermediate(String input, String src, String dest, String method) throws Exception {
    buildMap(input);

    // Example format for `output`: "US:BR"

    Set<String> visited = new HashSet<>();
    List<String> ret = new ArrayList<>();
    dfs(src, dest, 0, visited, new StringBuilder(src), new StringBuilder(), ret);

    // If no valid route is found, throw an exception
    if (ret.isEmpty()) {
      throw new Exception("No valid route found from " + src + " to " + dest);
    }

    return String.join(",\n", ret); // Return all valid routes found
  }

  public void dfs(String src, String dest, int cost, Set<String> visited,
      StringBuilder currentPath, StringBuilder methodPath,  List<String> ret) {
    if (src.equals(dest)) {
      ret.add(generateOutput(currentPath.toString(), methodPath.toString(), cost));
    }

    visited.add(src);
    if (map.containsKey(src)) {
      for (Edge neighbor : map.get(src)) {
        if (!visited.contains(neighbor.dest)) {

          int originalPathLength = currentPath.length();
          int originalMethodLength = methodPath.length();
          int newCost = cost + neighbor.cost;
          currentPath.append(" -> ").append(neighbor.dest);
          if (!methodPath.isEmpty()) {
            methodPath.append(" -> ");
          }
          methodPath.append(neighbor.method);
          dfs(neighbor.dest, dest, newCost, visited, currentPath, methodPath, ret);

          // if (result != null) return result; // If valid route found, return immediately

          // backtracking
          currentPath.setLength(originalPathLength);
          methodPath.setLength(originalMethodLength);
        }
      }
    }
    visited.remove(src); // Backtrack
  }

  public String generateOutput(String route, String method, int cost) {
    StringBuilder result = new StringBuilder();
    result.append("{\n");
    result.append(" \"route\": \"").append(route).append("\",\n");
    result.append(" \"method\": \"").append(method).append("\",\n");
    result.append(" \"cost\": ").append(cost).append("\n");

    result.append("}");
    return result.toString();
  }

  public static void main(String[] args) throws Exception {
    MultipleIntermediates mi = new MultipleIntermediates();
    String input = "US:CA:Car:300,CA:MX:Bus:200,MX:BR:Flight:800";
    String src = "US";
    String dest = "BR";
    String method = "Car";
    try {
      System.out.println(mi.CostMultiIntermediate(input, src, dest, method));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
