package Databricks.QPS;

import java.util.HashMap;
import java.util.LinkedList;

class MeasureQPS {
  private static final int MAX_REQUESTS = 300; // 5 minutes * 60 seconds = 300 requests
  private HashMap<String, LinkedList<Long>> requestMap; // Map to store request history per key

  public MeasureQPS() {
    requestMap = new HashMap<>();
  }

  // Circular buffer implementation: Insert timestamp into the buffer for a given key
  public void put(String key, long timestamp) {
    requestMap.putIfAbsent(key, new LinkedList<>());

    LinkedList<Long> buffer = requestMap.get(key);

    // If buffer is full, remove the oldest timestamp
    if (buffer.size() == MAX_REQUESTS) {
      buffer.poll(); // Removes the first (oldest) element
    }

    // Add the new timestamp to the buffer
    buffer.add(timestamp);
  }

  // Returns the number of requests for a given key in the last 5 minutes
  public int get(String key, long timestamp) {
    LinkedList<Long> buffer = requestMap.getOrDefault(key, new LinkedList<>());

    // Remove old entries that are out of the 5-minute window
    while (!buffer.isEmpty() && buffer.getFirst() <= timestamp - 300000) {
      buffer.pollFirst(); // Removes elements older than 5 minutes
    }

    return buffer.size(); // Return the number of requests in the last 5 minutes
  }

  // Measure the QPS (total number of requests) for all keys combined in the last 5 minutes
  public int measure_get(long timestamp) {
    int total = 0;
    for (String key : requestMap.keySet()) {
      total += get(key, timestamp);
    }
    return total;
  }

  // Measure the QPS for all `put` operations (total number of requests) in the last 5 minutes
  public int measure_put(long timestamp) {
    int total = 0;
    for (String key : requestMap.keySet()) {
      total += get(key, timestamp); // This assumes `put` uses same timestamp mechanism
    }
    return total;
  }

  public static void main(String[] args) {
    MeasureQPS measureQPS = new MeasureQPS();

    long currentTime = System.currentTimeMillis();

    // Simulate 5 requests for key "key1"
    for (int i = 0; i < 5; i++) {
      measureQPS.put("key1", currentTime - i * 1000);  // 1 request every second
    }

    // Measure QPS for "key1" after some time
    System.out.println("QPS for key1: " + measureQPS.get("key1", currentTime));  // QPS for "key1"
    System.out.println("Total QPS for all keys: " + measureQPS.measure_get(currentTime)); // Total QPS for all keys
  }
}
