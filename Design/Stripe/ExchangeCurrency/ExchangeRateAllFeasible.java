package Stripe.ExchangeCurrency;

import java.util.*;

public class ExchangeRateAllFeasible {
  public static class Edge {
    public double rate;
    public String node;

    public Edge(double rate, String toNext) {
      this.rate = rate;
      this.node = toNext;
    }
  }

  Map<String, List<Edge>> map = new HashMap<>();

  // Method to build the exchange rate map
  public void buildMap(String input) {
    String[] rates = input.split(",");
    for (String rate : rates) {
      String[] parts = rate.split(":");
      String currency = parts[0];
      String toCurrency = parts[1];
      double currencyRate = Double.parseDouble(parts[2]);

      // Add both direct and reverse edges
      map.computeIfAbsent(currency, k -> new ArrayList<>()).add(new Edge(currencyRate, toCurrency));
      map.computeIfAbsent(toCurrency, k -> new ArrayList<>()).add(new Edge(1 / currencyRate, currency));
    }
  }

  // Method to find and print all exchange rate combinations between two currencies
  public String ExchangeRate(String input, String fromCurrency, String toCurrency) throws Exception {
    buildMap(input);

    // set to keep track of all found paths
    Set<String> paths = new HashSet<>();
    Set<String> visited = new HashSet<>();

    // Start the DFS traversal from the source currency
    dfs(fromCurrency, toCurrency, 1.0, visited, paths, new StringBuilder(fromCurrency));

    // Print all paths found
    // If no valid route is found, throw an exception
    if (paths.isEmpty()) {
      throw new Exception("No valid route found from " + fromCurrency + " to " + toCurrency);
    }

    return String.join(",\n", paths);
  }

  // Depth-First Search method to find all paths
  private void dfs(String current, String target, double rate, Set<String> visited, Set<String> paths,
      StringBuilder currentPath) {
    // If we reach the target, save the current path and its rate
    if (current.equals(target)) {
      // Store the path with the rate
      paths.add(generateOutput(currentPath.toString(), rate));
      return;
    }

    visited.add(current);
    // Explore neighbors of the current currency
    for (Edge neighbor : map.getOrDefault(current, new ArrayList<>())) {
      // To avoid duplicate paths, we check if the next node has been visited
      if (!visited.contains(neighbor.node)) {
        // Append neighbor to the current path
        currentPath.append(" -> ").append(neighbor.node);
        // Recursive DFS call
        dfs(neighbor.node, target, rate * neighbor.rate, visited, paths, currentPath);
        // Backtrack to the previous state
        currentPath.setLength(currentPath.length() - neighbor.node.length() - 4); // " -> " is 4 characters
      }
    }

    visited.remove(current); // Unmark the node for other paths
  }

  // Generate the output format
  public String generateOutput(String route, double cost) {
    StringBuilder result = new StringBuilder();
    result.append("{\n");
    result.append(" \"route\": \"").append(route).append("\",\n");
    result.append(" \"cost\": ").append(cost).append("\n");
    result.append("}");
    return result.toString();
  }

  public static void main(String[] args) throws Exception {
    ExchangeRateAllFeasible exchangeRate = new ExchangeRateAllFeasible();
    String input = "USD:CAD:1.26,USD:AUD:0.75,USD:JPY:109.23";

    try {
      // Test direct exchange rate
      System.out.println("Exchange rate paths from USD to JPY:");
      String result1 = exchangeRate.ExchangeRate(input, "USD", "JPY");
      System.out.println(result1);  // Print the result

      // Test with an indirect relationship
      System.out.println("\nExchange rate paths from CAD to JPY:");
      String result2 = exchangeRate.ExchangeRate(input, "CAD", "JPY");
      System.out.println(result2);  // Print the result
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
