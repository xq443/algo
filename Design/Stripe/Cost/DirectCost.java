package Cost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectCost {
  public static class Edge {
    String src;
    String dest;
    String method;
    int cost;

    public Edge(String src, String dest, String method, int cost) {
      this.src = src;
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
      map.computeIfAbsent(src, k -> new ArrayList<>()).add(new Edge(src, dest, method, cost));
      map.computeIfAbsent(dest, k -> new ArrayList<>()).add(new Edge(dest, src, method, cost));
    }
  }

  public int CostDirect(String input, String output) throws Exception {
    buildMap(input);
    int retCost = Integer.MAX_VALUE;

    // parse the output: "UK:US:DHL"
    String[] group = output.split(":");
    String src = group[0];
    String dest = group[1];

    if (map.containsKey(src)) {
      for (Edge next : map.get(src)) {
        if (next.dest.equals(dest)) {
          retCost = next.cost;
        }
      }
    }
    return retCost;
  }

  public String CostOneIntermediate(String input, String output) {
    buildMap(input);

    // parse the output: "UK:US:DHL"
    String[] group = output.split(":");
    String src = group[0];
    String dest = group[1];

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
    return generateOutput(null, null, 0); // No valid route found
  }

  public String generateOutput(String route, String method, int cost) {
    StringBuilder result = new StringBuilder();
    result.append("{\n");
    if (route != null) {
      result.append(" \"route\": \"").append(route).append("\",\n");
      result.append(" \"method\": \"").append(method).append("\",\n");
      result.append(" \"cost\": ").append(cost).append("\n");
    } else {
      result.append(" error: \"No valid route found.\"\n");
    }
    result.append("}");
    return result.toString();
  }

  public static void main(String[] args) throws Exception {
    try {
      DirectCost cost = new DirectCost();
      String input = "US:UK:UPS:4,US:UK:DHL:5,UK:CA:FedEx:10,AU:JP:DHL:20,US:JP:DHL:20,CA:JP:DHL:20";
      String directOutput = "UK:US:DHL";
      String intermediateOutput = "US:CA";
      System.out.println(cost.CostDirect(input, directOutput));
      String outputIntermediate = cost.CostOneIntermediate(input, intermediateOutput);
      System.out.println(outputIntermediate); // Print the output for one intermediate
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
