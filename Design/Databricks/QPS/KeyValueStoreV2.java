package Databricks.QPS;

import java.util.HashMap;
import java.util.Map;

interface TimeGenerator {
  long currentTimeSeconds();
}

class Gen implements TimeGenerator {
  private long cur;

  // Set current time
  public void setTime(long t) {
    this.cur = t;
  }

  // Get current time in seconds
  @Override
  public long currentTimeSeconds() {
    return this.cur;
  }
}

public class KeyValueStoreV2 {
  // Key-value store
  private Map<String, String> map;

  // Arrays to record put operation timestamps and counts
  private Slot[] putSls;
  private Slot[] getSls;

  // Slot class representing a timestamp and the number of operations at that timestamp
  private static class Slot {
    long ts; // Timestamp (seconds)
    int cnt; // Operation count

    Slot(long ts, int cnt) {
      this.ts = ts;
      this.cnt = cnt;
    }
  }

  // Monitoring time window in seconds (5 minutes)
  private static final int WND = 300;

  // Time generator
  private TimeGenerator tg;

  // Constructor to initialize the key-value store and operation arrays
  public KeyValueStoreV2(TimeGenerator tg) {
    this.tg = tg;
    map = new HashMap<>();
    putSls = new Slot[WND];
    getSls = new Slot[WND];
    for (int i = 0; i < WND; i++) {
      putSls[i] = new Slot(0, 0);
      getSls[i] = new Slot(0, 0);
    }
  }

  // Insert key-value pair and record put operation
  public void put(String key, String val) {
    map.put(key, val);
    recOp(putSls);
  }

  // Get value for key and record get operation
  public String get(String key) {
    recOp(getSls);
    return map.get(key);
  }

  // Measure average QPS for put operations over the past 5 minutes
  public double mPut() {
    return calcQPS(putSls);
  }

  // Measure average QPS for get operations over the past 5 minutes
  public double mGet() {
    return calcQPS(getSls);
  }

  // Record an operation by updating the relevant timestamp and count
  private void recOp(Slot[] sls) {
    long cur = tg.currentTimeSeconds(); // Get current time in seconds
    int idx = (int) (cur % WND); // Calculate index based on current time
    synchronized (sls) { // Ensure thread safety
      if (sls[idx].ts == cur) {
        sls[idx].cnt++; // Increment count if within the same second
      } else {
        sls[idx].ts = cur; // Reset timestamp
        sls[idx].cnt = 1; // Reset count
      }
    }
  }

  // Calculate average QPS over the past 5 minutes
  private double calcQPS(Slot[] sls) {
    long cur = tg.currentTimeSeconds(); // Get current time in seconds
    int total = 0;
    synchronized (sls) { // Ensure thread safety
      for (int i = 0; i < WND; i++) {
        if (cur - sls[i].ts < WND) { // Include operations within 5 minutes
          total += sls[i].cnt; // Accumulate operation count
        }
      }
    }
    return total / (double) WND; // Calculate average QPS
  }

  // Main method for running test cases
  public static void main(String[] args) throws InterruptedException {
    Gen sg = new Gen(); // Create time generator
    KeyValueStoreV2 store = new KeyValueStoreV2(sg); // Create store with time generator

    // Test case 1: Basic put and get operations
    sg.setTime(60);
    store.put("a", "1");
    sg.setTime(120);
    store.put("b", "2");
    sg.setTime(180);
    store.get("a");
    sg.setTime(240);
    store.get("b");
    sg.setTime(300);
    store.get("c"); // Get non-existent key
    System.out.println("Test Case 1:");
    System.out.println("Put QPS: " + store.mPut()); // Expected: ~0.006 (2/300)
    System.out.println("Get QPS: " + store.mGet()); // Expected: ~0.01 (3/300)

    // Test case 2: Multiple operations within the same second
    sg.setTime(360);
    for (int i = 0; i < 100; i++) {
      store.put("key" + i, "val" + i);
      store.get("key" + i);
    }
    System.out.println("\nTest Case 2:");
    System.out.println("Put QPS: " + store.mPut()); // Expected: ~0.34 (100/300)
    System.out.println("Get QPS: " + store.mGet()); // Expected: ~0.34 (100/300)

    // Test case 3: Operations distributed over time
    sg.setTime(420);
    store.put("d", "4");
    sg.setTime(422);
    store.get("d");
    sg.setTime(424);
    store.put("e", "5");
    sg.setTime(426);
    store.get("e");
    System.out.println("\nTest Case 3:");
    System.out.println("Put QPS: " + store.mPut()); // Expected: slightly higher than previous
    System.out.println("Get QPS: " + store.mGet());

    // Test case 4: Boundary condition, operation exactly 5 minutes ago
    long cur = 500;
    int idx = (int) (cur % WND);
    synchronized (store.putSls) {
      store.putSls[idx] = new Slot(cur - WND, 50); // Should not be included
    }
    synchronized (store.getSls) {
      store.getSls[idx] = new Slot(cur - WND, 50); // Should not be included
    }
    sg.setTime(500);
    System.out.println("\nTest Case 4:");
    System.out.println("Put QPS after adding old operation: " + store.mPut()); // Should exclude 50
    System.out.println("Get QPS after adding old operation: " + store.mGet()); // Should exclude 50

    // Test case 5: High-frequency operations
    sg.setTime(600);
    for (int i = 0; i < 1000; i++) {
      store.put("high" + i, "val" + i);
      store.get("high" + i);
    }
    System.out.println("\nTest Case 5:");
    System.out.println("Put QPS after high-frequency operations: " + store.mPut()); // Expected: ~3.34
    System.out.println("Get QPS after high-frequency operations: " + store.mGet()); // Expected: ~3.34

    // Test case 6: No operations
    KeyValueStoreV2 emptyStore = new KeyValueStoreV2(sg);
    System.out.println("\nTest Case 6:");
    System.out.println("Put QPS (empty): " + emptyStore.mPut()); // Expected: 0.0
    System.out.println("Get QPS (empty): " + emptyStore.mGet()); // Expected: 0.0

    // Test case 7: Boundary condition operation within the window
    store.put("boundary", "boundary");
    store.get("boundary");
    synchronized (store.putSls) {
      store.putSls[idx] = new Slot(cur - WND + 1, 1); // Should still be included
    }
    synchronized (store.getSls) {
      store.getSls[idx] = new Slot(cur - WND + 1, 1); // Should still be included
    }
    sg.setTime(601);
    System.out.println("\nTest Case 7:");
    System.out.println("Put QPS after boundary operation: " + store.mPut());
    System.out.println("Get QPS after boundary operation: " + store.mGet());
  }
}
