package Databricks.CustomerRevenue;

import java.util.*;

class RevenueGraph {
  // User class to store user ID and revenue
  class User {
    int id;
    int rev;
    List<User> referrals; // List of referred users

    User(int id, int rev) {
      this.id = id;
      this.rev = rev;
      this.referrals = new ArrayList<>();
    }
  }

  // Auto-incrementing user ID counter
  private int cnt;
  // Map to store all users by their ID
  private Map<Integer, User> usrMap;
  // TreeMap to store users by their revenue, sorted by revenue
  private TreeMap<Integer, Set<User>> revMap;

  // Constructor
  public RevenueGraph() {
    cnt = 1;
    usrMap = new HashMap<>();
    revMap = new TreeMap<>();
  }

  // Insert a new user with given revenue
  public int insert(int revenue) {
    User usr = new User(cnt, revenue);
    usrMap.put(cnt, usr);
    revMap.computeIfAbsent(revenue, x -> new HashSet<>()).add(usr);
    return cnt++;
  }

  // Insert a new user with given revenue and associate with a referring user
  public int insert(int rev, int refId) {
    // Check if referring user exists
    if (!usrMap.containsKey(refId)) {
      throw new IllegalArgumentException("Referring user ID does not exist");
    }
    // Create new user
    User newUsr = new User(cnt, rev);
    usrMap.put(cnt, newUsr);
    revMap.computeIfAbsent(rev, x -> new HashSet<>()).add(newUsr);
    // Update referring user's revenue
    User refUsr = usrMap.get(refId);
    refUsr.referrals.add(newUsr); // Add new user to referrals list
    // Remove old revenue record
    revMap.get(refUsr.rev).remove(refUsr);
    if (revMap.get(refUsr.rev).isEmpty()) {
      revMap.remove(refUsr.rev);
    }
    // Update revenue
    refUsr.rev += rev;
    // Add new revenue record
    revMap.computeIfAbsent(refUsr.rev, x -> new HashSet<>()).add(refUsr);
    return cnt++;
  }

  // Get top k users with revenue below the given threshold
  public List<Integer> get(int k, int rev) {
    List<Integer> res = new ArrayList<>();
    // Get all entries with revenue below the threshold, in descending order
    NavigableMap<Integer, Set<User>> subMap = new TreeMap<>(revMap.headMap(rev, false)).descendingMap();
    for (Map.Entry<Integer, Set<User>> entry : subMap.entrySet()) {
      for (User usr : entry.getValue()) {
        res.add(usr.id);
        if (res.size() == k) return res;
      }
    }
    return res;
  }

  // Calculate the revenue sum within a given depth from a starting user ID
  public int getRevenueSum(int startId, int maxDepth) {
    if (!usrMap.containsKey(startId)) {
      throw new IllegalArgumentException("User ID does not exist");
    }

    int totalRevenue = 0;
    Queue<User> queue = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    queue.add(usrMap.get(startId));
    visited.add(startId);

    int currentDepth = 0;
    while (!queue.isEmpty() && currentDepth <= maxDepth) {
      int levelSize = queue.size();
      for (int i = 0; i < levelSize; i++) {
        User currentUser = queue.poll();
        assert currentUser != null;
        totalRevenue += currentUser.rev;
        for (User referral : currentUser.referrals) {
          if (!visited.contains(referral.id)) {
            queue.add(referral);
            visited.add(referral.id);
          }
        }
      }
      currentDepth++;
    }

    return totalRevenue;
  }

  public static void main(String[] args) {
    RevenueGraph ra = new RevenueGraph();

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
    System.out.println(ra.get(3, 200)); // Expected output: [5, 3, 4]

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
    RevenueAssociation raEmpty = new RevenueAssociation();
    System.out.println(raEmpty.get(1, 100)); // Expected output: []

    // Test case 9: Revenue sum within depth
    System.out.println("Test Case 9:");
    ra.insert(100, 1); // ID=9, ID=1's revenue becomes 250
    ra.insert(50, 9); // ID=10, ID=9's revenue becomes 150
    System.out.println(ra.getRevenueSum(1, 2)); // Expected output: sum of revenues within depth 2 from ID=1
  }
}