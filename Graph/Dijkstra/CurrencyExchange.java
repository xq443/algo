import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class CurrencyExchange {
  public static class edge{
    Double rate;
    String node;

    public edge(Double rate, String next) {
      this.rate = rate;
      this.node = next;
    }
  }
  // 使用Dijkstra算法求解最大汇率路径
  public double findBestExchangeRate(String start, String end, double amount) {
    // build up graph
    Map<String, List<edge>> graph = new HashMap<>();
    graph.computeIfAbsent("USD", k -> new ArrayList<>()).add(new edge(1.3, "CAD"));
    graph.computeIfAbsent("CAD", k -> new ArrayList<>()).add(new edge(1/1.3, "USD"));
    graph.computeIfAbsent("USD", k -> new ArrayList<>()).add(new edge(0.8, "EUR"));
    graph.computeIfAbsent("EUR", k -> new ArrayList<>()).add(new edge(100.0,"JPY"));
    graph.computeIfAbsent("EUR", k -> new ArrayList<>()).add(new edge(1 / 0.8, "USD"));
    graph.computeIfAbsent("JPY", k -> new ArrayList<>()).add(new edge(1 / 100.0, "EUR"));

    // maintain a max heap, cause the max rate is needed
    PriorityQueue<edge> pq = new PriorityQueue<>((a,b) -> Double.compare(b.rate, a.rate));
    pq.offer(new edge(1.0, start));

    // the best rate map to store the currency exchange
    Map<String, Double> bestRate = new HashMap<>();
    bestRate.put(start, 1.0);

    while(!pq.isEmpty()) {
      edge cur = pq.poll();
      String currency = cur.node;
      Double rate = cur.rate;

      if(currency.equals(end)) {
        return rate * amount;
      }

      if(graph.containsKey(currency)) {
        for(edge neighbour : graph.get(currency)) {
          double newRate = rate * neighbour.rate;
          if(newRate > bestRate.getOrDefault((neighbour.node), 0.0)) {
            bestRate.put(neighbour.node, newRate);
            pq.offer(new edge(newRate, neighbour.node));
          }
        }
      }
    }
    return 0.0;
  }

  public static void main(String[] args) {
    CurrencyExchange obj = new CurrencyExchange();
    String start = "USD";
    String end = "JPY";
    double amount = 100;
    System.out.println(obj.findBestExchangeRate(start, end, amount));
  }
}

//Time Complexity: O((V + E) log V)
//Space Complexity: O(V + E)
//V is the number of currencies (vertices)
//E is the number of exchange rates (edges)
