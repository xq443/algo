package Stripe.ExchangeCurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeRateDirect {
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

  // Method to get the direct exchange rate between two currencies
  public Double ExchangeRate(String input, String fromCurrency, String toCurrency) throws Exception {
    buildMap(input);

    if (map.containsKey(fromCurrency)) {
      for (Edge edge : map.get(fromCurrency)) {
        if (edge.node.equals(toCurrency)) {
          return Math.round(edge.rate * 1000) / 1000.0;
        }
      }
    }
    throw new Exception("No valid rate found");
  }

  // Method to find the exchange rate with one intermediate currency
  public String ExchangeRateOneIntermediate(String input, String fromCurrency, String toCurrency) throws Exception {
    buildMap(input);

    // Iterate through all edges for the fromCurrency
    if (map.containsKey(fromCurrency)) {
      for (Edge edge : map.get(fromCurrency)) {
        String intermediateCurrency = edge.node;
        double currRate = edge.rate;

        // Check if there's a direct edge from intermediateCurrency to the toCurrency
        if (map.containsKey(intermediateCurrency)) {
          for (Edge next : map.get(intermediateCurrency)) {
            if (next.node.equals(toCurrency)) {
              // Calculate the cost using the two rates (from -> intermediate and intermediate -> to)
              double retCost = currRate * next.rate;
              String retRoute = fromCurrency + " -> " + intermediateCurrency + " -> " + toCurrency;
              return generateOutput(retRoute, retCost);
            }
          }
        }
      }
    }
    throw new Exception("No valid route found");
  }

  // Helper method to format the output as a JSON string
  public String generateOutput(String route, Double cost) {
    StringBuilder ret = new StringBuilder();
    ret.append("{\n");
    ret.append("     \"path\": \"").append(route).append("\",\n");
    ret.append("     \"cost\": \"").append(cost).append("\"\n");
    ret.append("}");
    return ret.toString();
  }

  // Main method to test the functionality
  public static void main(String[] args) {
    ExchangeRateDirect exchangeRate = new ExchangeRateDirect();
    String input = "USD:CAD:1.26,USD:AUD:0.75,USD:JPY:109.23,CAD:CHY:21";

    try {
      System.out.println(exchangeRate.ExchangeRateOneIntermediate(input, "USD", "CHY"));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
}
