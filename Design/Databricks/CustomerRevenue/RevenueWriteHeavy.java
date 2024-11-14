package Databricks.CustomerRevenue;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class RevenueWriteHeavy {
  // User class
  class User {
    int id;
    int rev;
    User(int id, int rev) {
      this.id = id;
      this.rev = rev;
    }
  }

  // Auto-incrementing user ID
  private AtomicInteger cnt;
  // Store all users
  private ConcurrentHashMap<Integer, User> usrMap;
  // Sorted map of users by revenue
  private ConcurrentSkipListMap<Integer, Set<User>> revMap;
  // Batch of insert operations
  private ConcurrentLinkedQueue<User> batchQueue;
  // Batch size
  private final int BATCH_SIZE = 10;

  // Constructor
  public RevenueWriteHeavy() {
    cnt = new AtomicInteger(1);
    usrMap = new ConcurrentHashMap<>();
    revMap = new ConcurrentSkipListMap<>();
    batchQueue = new ConcurrentLinkedQueue<>();
  }

  public int insert(int revenue) {
    User usr = new User(cnt.getAndIncrement(), revenue);
    batchQueue.add(usr);
    if (batchQueue.size() >= BATCH_SIZE) {
      processBatch();
    }
    return usr.id;
  }

  public int insert(int rev, int refId) {
    if (!usrMap.containsKey(refId)) {
      throw new IllegalArgumentException("Referring user ID does not exist");
    }
    User newUsr = new User(cnt.getAndIncrement(), rev);
    batchQueue.add(newUsr);
    if (batchQueue.size() >= BATCH_SIZE) {
      processBatch();
    }
    User refUsr = usrMap.get(refId);
    revMap.get(refUsr.rev).remove(refUsr);
    if (revMap.get(refUsr.rev).isEmpty()) {
      revMap.remove(refUsr.rev);
    }
    refUsr.rev += rev;
    revMap.computeIfAbsent(refUsr.rev, x -> ConcurrentHashMap.newKeySet()).add(refUsr);
    return newUsr.id;
  }

  private void processBatch() {
    List<User> batch = new ArrayList<>();
    while (batch.size() < BATCH_SIZE && !batchQueue.isEmpty()) {
      User usr = batchQueue.poll();
      if (usr != null) {
        batch.add(usr);
      }
    }
    for (User usr : batch) {
      usrMap.put(usr.id, usr);
      revMap.computeIfAbsent(usr.rev, x -> ConcurrentHashMap.newKeySet()).add(usr);
    }
  }

  public List<Integer> get(int k, int rev) {
    List<Integer> res = new ArrayList<>();
    NavigableMap<Integer, Set<User>> subMap = revMap.headMap(rev, false).descendingMap();
    for (Map.Entry<Integer, Set<User>> entry : subMap.entrySet()) {
      for (User usr : entry.getValue()) {
        res.add(usr.id);
        if (res.size() == k) return res;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    RevenueWriteHeavy ra = new RevenueWriteHeavy();

    // Test case 1: Basic insertion and retrieval
    System.out.println("Test Case 1:");
    ra.insert(100); // ID=1
    ra.insert(200); // ID=2
    ra.insert(150); // ID=3
    System.out.println(ra.get(2, 200)); // Expected output: [3, 1]

    // Test case 2: Referral insertion
    System.out.println("Test Case 2:");
    ra.insert(50, 1); // ID=4, ID=1's revenue becomes 150
    System.out.println(ra.get(3, 200)); // Expected output: [3, 4, 1]

    // Test case 3: k greater than number of users
    System.out.println("Test Case 3:");
    System.out.println(ra.get(10, 300)); // Expected output: [2, 1, 3, 4]

    // Test case 4: No users below revenue threshold
    System.out.println("Test Case 4:");
    System.out.println(ra.get(2, 50)); // Expected output: []

    // Test case 5: Inserting same revenue
    System.out.println("Test Case 5:");
    ra.insert(150); // ID=5
    ra.insert(150, 2); // ID=6, ID=2's revenue becomes 350
    System.out.println(ra.get(3, 200)); // Expected output: [5, 1, 3]

    // Test case 6: Referring user does not exist
    System.out.println("Test Case 6:");
    try {
      ra.insert(100, 10); // Referring user ID=10 does not exist
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage()); // Expected output: error message
    }

    // Test case 7: Revenue boundaries
    System.out.println("Test Case 7:");
    ra.insert(0); // ID=7
    ra.insert(Integer.MAX_VALUE, 7); // ID=8, ID=7's revenue becomes 2147483647
    System.out.println(ra.get(5, Integer.MAX_VALUE)); // Expected output: top 5 IDs below max value

    // Test case 8: No users
    System.out.println("Test Case 8:");
    RevenueWriteHeavy raEmpty = new RevenueWriteHeavy();
    System.out.println(raEmpty.get(1, 100)); // Expected output: []
  }
}