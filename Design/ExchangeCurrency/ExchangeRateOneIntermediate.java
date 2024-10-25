package ExchangeCurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeRateOneIntermediate {
  public static class Edge {
    public double rate;
    public String toNext;

    public Edge(double rate, String toNext) {
      this.rate = rate;
      this.toNext = toNext;
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
      map.computeIfAbsent(currency, k -> new ArrayList<>())
          .add(new Edge(currencyRate, toCurrency));
      map.computeIfAbsent(toCurrency, k -> new ArrayList<>())
          .add(new Edge(1 / currencyRate, currency));
    }
  }

  public Double getCurrency(String currency, String toCurrency) {
    if (map.containsKey(currency)) {
      for (Edge edge : map.get(currency)) {
        if (edge.toNext.equals(toCurrency)) {
          return edge.rate;
        }
      }
    }
    return null; // Return null if the currency pair is not found
  }

  public String ExchangeRate(String input, String output) {
    buildMap(input);
    String[] response = output.split(":");
    String currency = response[0];
    String toCurrency = response[1];
    StringBuilder ret = new StringBuilder();

    // Check for direct relationship
    Double directRate = getCurrency(currency, toCurrency);
    if (directRate != null) {
      printPath(Map.of(toCurrency, currency), currency, toCurrency);
      return ret.append("Direct Rate: ").append(String.format("%.3f", directRate)).append("\n").toString();
    }

    // Map to hold intermediate rates and their paths
    Map<String, Double> intermediateRates = new HashMap<>();
    Map<String, String> parent = new HashMap<>();

    // Iterate through all possible intermediates
    for (String intermediate : map.keySet()) {
      Double fromSource = getCurrency(currency, intermediate);
      Double toTarget = getCurrency(intermediate, toCurrency);
      if (fromSource != null && toTarget != null) { // Check if rates are valid
        double totalRate = fromSource * toTarget;
        intermediateRates.put(intermediate, totalRate);
        parent.put(intermediate, currency);
        parent.put(toCurrency, intermediate); // Update parent map for path

        // Print the total rate and path using the printPath method
        printPath(parent, currency, toCurrency); // Print the path
        return ret.append("Intermediate Rate: ").append(String.format("%.3f", totalRate)).toString();
      }
    }

    // Print message if no valid rates were found
    if (ret.length() == 0) {
      ret.append("No valid exchange rate found.");
    }

    return ret.toString();
  }

  // Method to print the path from start to end using parent map
  private void printPath(Map<String, String> parent, String start, String end) {
    List<String> path = new ArrayList<>();
    for (String at = end; at != null; at = parent.get(at)) {
      path.add(at);
    }
    Collections.reverse(path); // Reverse the list to get the correct order
    System.out.println("Path: " + String.join(" -> ", path));
  }

  public static void main(String[] args) {
    ExchangeRateOneIntermediate exchangeRate = new ExchangeRateOneIntermediate();
    String input = "USD:CAD:1.26,USD:AUD:0.75,USD:JPY:109.23,CAD:JPY:90";

    // Test using an indirect relationship with an intermediate
    String output = "CAD:AUD"; // Should show paths through intermediates
    System.out.println(exchangeRate.ExchangeRate(input, output));
  }
}
