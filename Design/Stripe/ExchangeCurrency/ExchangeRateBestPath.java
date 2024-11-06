package Stripe.ExchangeCurrency;

import java.util.*;

public class ExchangeRateBestPath {
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

  // Method to find the best exchange rate for a specific currency-to-currency pair
  public Double findBestExchangeRate(String input, String fromCurrency, String toCurrency)
      throws Exception {
    buildMap(input);

    // Priority queue to keep track of the max rate path
    PriorityQueue<Edge> queue = new PriorityQueue<>((a, b) -> Double.compare(b.rate, a.rate));
    queue.add(new Edge(1.0, fromCurrency));

    // Map to keep track of the path for reconstruction
    Map<String, String> parent = new HashMap<>();

    // track the highest rate achievable for the currency
    double bestRate = 0.0;

    while (!queue.isEmpty()) {
      Edge edge = queue.poll();
      String currency = edge.node;
      double rate = edge.rate;

      // Check if we reached the target currency
      if (currency.equals(toCurrency)) {
        bestRate = rate; // Update the best rate found
      }

      // Explore neighbors of the current currency
      for (Edge neighbor : map.getOrDefault(currency, new ArrayList<>())) {
        double newRate = rate * neighbor.rate;

        // Only proceed if this new rate is better than any rate we've found before for the neighbor
        if (newRate > bestRate) {
          bestRate = newRate;
          queue.add(new Edge(newRate, neighbor.node));
          // Update the parent map only when a better rate is found
          parent.put(neighbor.node, currency);
        }
      }
    }
    // Print the path if a valid route was found
    if (bestRate > 0) {
      printPath(parent, fromCurrency, toCurrency); // Print the path
      return Math.round(bestRate * 100.0) / 100.0;
    }
    throw new Exception("No path found from " + fromCurrency + " to " + toCurrency);
  }

  // Method to print the path from start to end
  private void printPath(Map<String, String> parent, String start, String end) {
    List<String> path = new ArrayList<>();
    for (String at = end; at != null; at = parent.get(at)) {
      path.add(at);
    }
    Collections.reverse(path); // Reverse the list to get the correct order
    System.out.println("Path: " + String.join("->", path));
  }


  public static void main(String[] args) throws Exception {
    ExchangeRateBestPath exchangeRate = new ExchangeRateBestPath();
    String input = "USD:CAD:1.26,USD:AUD:0.75,USD:JPY:10000,CAD:JPY:90";

    // Finding best rate for specific pairs
    System.out.println("Best exchange rate from USD to JPY: " + exchangeRate.findBestExchangeRate(input, "USD", "JPY"));
    System.out.println("Best exchange rate from CAD to JPY: " + exchangeRate.findBestExchangeRate(input, "CAD", "JPY"));
  }
}