package Stripe.Cost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectCost {
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
      //map.computeIfAbsent(dest, k -> new ArrayList<>()).add(new Edge(dest, src, method, cost));
    }
  }

  public int CostDirect(String input, String src, String dest, String method) throws Exception {
    buildMap(input);

    // parse the output: "UK:US:DHL"
    if (map.containsKey(src)) {
      for (Edge next : map.get(src)) {
        if (next.dest.equals(dest) && next.method.equals(method)) {
          return next.cost;
        }
      }
    }
    throw new Exception("No valid route found");
  }

  public String CostOneIntermediate(String input, String src, String dest, String method) throws Exception {
    buildMap(input);

    // Check for one intermediate
    if (map.containsKey(src)) {
      for (Edge intermediate : map.get(src)) {
        for (Edge next : map.get(intermediate.dest)) {
          if (next.dest.equals(dest)) {
            int retCost = intermediate.cost + next.cost;;
            String retRoute = src + " -> " + intermediate.dest + " -> " + next.dest;
            String retMethod = intermediate.method + " -> " + next.method;
            return generateOutput(retRoute, retMethod, retCost);
          }
        }
      }
    }
    throw new Exception("No valid route found");
  }

  public String generateOutput(String route, String method, int cost) {
    StringBuilder result = new StringBuilder();
    result.append("{\n");

    result.append("     \"route\": \"").append(route).append("\",\n");
    result.append("     \"method\": \"").append(method).append("\",\n");
    result.append("     \"cost\": ").append(cost).append("\n");

    result.append("}");
    return result.toString();
  }

  public static void main(String[] args) throws Exception {
    try {
      DirectCost cost = new DirectCost();
      String input = "US:UK:UPS:4,US:UK:DHL:5,UK:CA:FedEx:10,AU:JP:DHL:20,US:JP:DHL:20,CA:JP:DHL:20";
      String src = "UK";
      String dest = "CA";
      String method = "FedEx";
      System.out.println(cost.CostDirect(input, src, dest, method));
      String outputIntermediate = cost.CostOneIntermediate(input, src, dest, method);
      System.out.println(outputIntermediate); // Print the output for one intermediate
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
