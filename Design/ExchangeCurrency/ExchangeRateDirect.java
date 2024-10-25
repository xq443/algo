package ExchangeCurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeRateDirect {
  public static class Edge{
    public double rate;
    public String toNext;

    public Edge(double rate, String toNext) {
      this.rate = rate;
      this.toNext = toNext;
    }
  }

  Map<String, List<Edge>> map = new HashMap<>();

  public String ExchangeRate(String input, String output){
    String[] rates = input.split(",");
    for(String rate : rates){
      String currency = rate.split(":")[0];
      String toCurrency = rate.split(":")[1];
      double currencyRate = Double.parseDouble(rate.split(":")[2]);
      map.computeIfAbsent(currency, k -> new ArrayList<>()).add(new Edge(currencyRate, toCurrency));
      map.computeIfAbsent(toCurrency, k -> new ArrayList<>()).add(new Edge(1 / currencyRate, currency));
    }

    String[] response = output.split(":");
    String currency = response[0];
    String toCurrency = response[1];
    StringBuilder ret = new StringBuilder();
    boolean found = false;

    if(currency.equals(toCurrency)){
      ret.append(output).append(":").append(1.0);
      return ret.toString();
    }

    if(map.containsKey(currency)){
      for(Edge edge : map.getOrDefault(currency, new ArrayList<>())){
        if(edge.toNext.equals(toCurrency)) {
          double retRate = Math.round(edge.rate * 1000) / 1000.0;
          ret.append(output).append(":").append(retRate);
          found = true; // Set flag to true if rate is found
          break; // Exit loop once we found the exchange rate
        }
      }
    }
    return (found) ? ret.toString() : "No valid rate";
  }

  public static void main(String[] args) {
    ExchangeRateDirect exchangeRate = new ExchangeRateDirect();
    String input = "USD:CAD:1.26,USD:AUD:0.75,USD:JPY:109.23";
    String output = "AUD:AUD";
    System.out.println(exchangeRate.ExchangeRate(input, output));
  }
}
