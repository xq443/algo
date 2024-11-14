package Databricks.CustomerRevenue;

import java.util.*;

class RevenueAssociation {
  // 用户类
  class User {
    int id;
    int rev;
    User(int id, int rev) {
      this.id = id;
      this.rev = rev;
    }
  }

  // 自增ID
  private int cnt;
  // 存储所有用户
  private Map<Integer, User> usrMap;
  // 按收入排序的TreeMap，键为收入，值为用户集合
  private TreeMap<Integer, Set<User>> revMap;

  // 构造函数
  public RevenueAssociation() {
    cnt = 1;
    usrMap = new HashMap<>();
    revMap = new TreeMap<>();
  }

  public int insert(int revenue) {
    User usr = new User(cnt, revenue);
    usrMap.put(cnt, usr);
    revMap.computeIfAbsent(revenue, x -> new HashSet<>()).add(usr);
    return cnt++;
  }

  public int insert(int rev, int refId) {
    // 检查推荐用户是否存在
    if (!usrMap.containsKey(refId)) {
      throw new IllegalArgumentException("推荐用户ID不存在");
    }
    // 创建新用户
    User newUsr = new User(cnt, rev);
    usrMap.put(cnt, newUsr);
    revMap.computeIfAbsent(rev, x -> new HashSet<>()).add(newUsr);
    // 更新推荐用户的收入
    User refUsr = usrMap.get(refId);
    // 移除旧收入记录
    revMap.get(refUsr.rev).remove(refUsr);
    if (revMap.get(refUsr.rev).isEmpty()) {
      revMap.remove(refUsr.rev);
    }
    // 更新收入
    refUsr.rev += rev;
    // 添加新的收入记录
    revMap.computeIfAbsent(refUsr.rev, x -> new HashSet<>()).add(refUsr);
    return cnt++;
  }

  public List<Integer> get(int k, int rev) {
    List<Integer> res = new ArrayList<>();
    // Get all entries with revenue below the threshold, in descending order
    // Returns a reverse order view of the mappings contained in this map.
    /**
     * descendingMap(): Returns a reverse order view of the mappings contained in this map.
     * subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive): Returns a view of the portion of this map whose keys range from fromKey to toKey.
     * headMap(K toKey, boolean inclusive): Returns a view of the portion of this map whose keys are less than (or equal to, if inclusive is true) toKey.
     */
    NavigableMap<Integer, Set<User>> subMap = new TreeMap<>(revMap.headMap(rev, false)).descendingMap();
    //TreeMap<Integer, Set<User>> subMap = (TreeMap<Integer, Set<User>>) new TreeMap<>(revMap.headMap(rev, false)).descendingMap();
    for (Map.Entry<Integer, Set<User>> entry : subMap.entrySet()) {
      for (User usr : entry.getValue()) {
        res.add(usr.id);
        if (res.size() == k) return res;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    RevenueAssociation ra = new RevenueAssociation();

    // 测试用例1：基本插入和获取
    System.out.println("Test Case 1:");
    ra.insert(100); // ID=1
    ra.insert(200); // ID=2
    ra.insert(150); // ID=3
    System.out.println(ra.get(2, 200)); // 应返回[3,1]

    // 测试用例2：关联插入
    System.out.println("Test Case 2:");
    ra.insert(50, 1); // ID=4，ID=1的收入变为150
    System.out.println(ra.get(3, 200)); // 应返回[3,4,1]

    // 测试用例3：k大于用户数量
    System.out.println("Test Case 3:");
    System.out.println(ra.get(10, 300)); // 应返回所有ID按收入降序[2,1,3,4]

    // 测试用例4：收入阈值无用户
    System.out.println("Test Case 4:");
    System.out.println(ra.get(2, 50)); // 应返回空列表[]

    // 测试用例5：插入相同收入
    System.out.println("Test Case 5:");
    ra.insert(150); // ID=5
    ra.insert(150, 2); // ID=6，ID=2的收入变为350
    System.out.println(ra.get(3, 200)); // 应返回[5,1,3]

    // 测试用例6：推荐用户不存在
    System.out.println("Test Case 6:");
    try {
      ra.insert(100, 10); // 推荐用户ID=10不存在
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage()); // 应输出错误信息
    }

    // 测试用例7：收入边界
    System.out.println("Test Case 7:");
    ra.insert(0); // ID=7
    ra.insert(Integer.MAX_VALUE, 7); // ID=8，ID=7的收入变为2147483647
    System.out.println(ra.get(5, Integer.MAX_VALUE)); // 应返回前5个收入低于最大值的ID

    // 测试用例8：无用户
    System.out.println("Test Case 8:");
    RevenueAssociation raEmpty = new RevenueAssociation();
    System.out.println(raEmpty.get(1, 100)); // 应返回空列表[]
  }
}
/**
 * Time Complexity Analysis
 * Insert Operations:
 *
 * insert(int revenue):
 *
 * HashMap Insertion: Adding a user to usrMap takes O(1) on average.
 * TreeMap Insertion: Adding a user to revMap takes O(log n).
 * Overall Time Complexity: O(log n)
 *
 * insert(int rev, int refId):
 *
 * HashMap Lookup: Checking if the referring user exists takes O(1).
 * HashMap Insertion: Adding a new user to usrMap takes O(1) on average.
 * TreeMap Insertion: Adding a new user to revMap takes O(log n).
 * TreeMap Removal: Removing the old revenue entry from revMap takes O(log n).
 * TreeMap Insertion: Adding the updated revenue entry to revMap takes O(log n).
 * Overall Time Complexity: O(log n)
 *
 * Read Operation:
 *
 * HashMap Lookup: Reading a user's information from usrMap takes O(1) on average.
 * Overall Time Complexity: O(1)
 * Get Operation:
 *
 * TreeMap Range Query: Using headMap to get entries with revenue below the threshold takes O(log n).
 * TreeMap Descending Map: Creating a descending view of the map takes O(1).
 * Iteration: Iterating over the entries to collect the top k users takes O(k).
 * Overall Time Complexity: O(k + log n)
 *
 * Summary
 * Insert Operations:
 *
 * insert(int revenue): O(log n)
 * insert(int rev, int refId): O(log n)
 *
 * Read Operation:
 *
 * HashMap Lookup: O(1)
 *
 * Get Operation:
 *
 * get(int k, int rev): O(k + log n)
 *
 * Read-Heavy Workloads: The current implementation is more suitable for read-heavy workloads due to the efficient range queries and sorted data provided by TreeMap.
 * The get method benefits from the sorted order and efficient range queries O(1).
 * Write-Heavy Workloads: For write-heavy workloads, the current implementation may face performance bottlenecks
 * due to the O(log n) complexity of TreeMap operations.
 * Optimizations such as batching writes or using ConcurrentHashMap for concurrent writes
 */