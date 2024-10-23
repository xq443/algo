public class DesignHashMap {

  // Making it static ensures that it can be accessed independently of any
  // instances of DesignHashMap.
  public static class Node {
    int key, value;
    Node next;
    public Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }
  Node[] bucket;
  int SIZE = 10000; // At most 10^4 calls will be made to put, get, and remove.
  /**
   * Initializes the object with an empty map.
   * The Node[] represents an array of head nodes.
   * Each index corresponds to a bucket in the hash table.
   * Each bucket can be visualized as the head of a linked list that stores
   * key-value pairs.
   */
  public DesignHashMap() { this.bucket = new Node[SIZE]; }

  /**
   * inserts a (key, value) pair into the HashMap.
   * If the key already exists in the map, update the corresponding value.
   * @param key
   * @param value
   */
  public void put(int key, int value) {
    int idx = getIdx(key);
    if (bucket[idx] == null) {
      Node node = new Node(-1, -1);
      bucket[idx] = node;
    }
    Node prev = findPrevNode(key, bucket[idx]);
    if (prev.next == null) {
      prev.next = new Node(key, value);
    } else {
      prev.next.value = value;
    }
  }

  /**
   * returns the value to which the specified key is mapped,
   * or -1 if this map contains no mapping for the key.
   * @param key
   * @return
   */
  int get(int key) {
    int idx = getIdx(key);
    if (bucket[idx] == null)
      return -1;
    Node prev = findPrevNode(key, bucket[idx]);
    if (prev.next == null)
      return -1;
    return prev.next.value;
  }

  /**
   * removes the key and its corresponding value if the map contains the mapping
   * for the key.
   * @param key
   */
  void remove(int key) {
    int idx = getIdx(key);
    if (bucket[idx] == null)
      return;
    Node prev = findPrevNode(key, bucket[idx]);
    if (prev.next != null) {
      prev.next = prev.next.next;
    }
  }

  /**
   * The % SIZE operation ensures that the index falls within the range of the
   * bucket array. When a collision occurs (i.e., two keys hash to the same
   * index), new nodes are added to the linked list corresponding to that index.
   * @param key
   * @return
   */
  public int getIdx(int key) { return Integer.hashCode(key) % SIZE; }
  public Node findPrevNode(int key, Node node) {
    Node curr = node;
    Node prev = null;
    while (curr != null && curr.key != key) {
      prev = curr;
      curr = curr.next;
    }
    return prev;
  }

  public static void main(String[] args) {
    DesignHashMap myHashMap = new DesignHashMap();
    myHashMap.put(1, 1); // The map is now [[1,1]]
    myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
    System.out.println(
        myHashMap.get(1)); // return 1, The map is now [[1,1], [2,2]]
    System.out.println(myHashMap.get(
        3)); // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
    myHashMap.put(
        2,
        1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
    System.out.println(
        myHashMap.get(2)); // return 1, The map is now [[1,1], [2,1]]
    myHashMap.remove(2);   // remove the mapping for 2, The map is now [[1,1]]
    System.out.println(myHashMap.get(
        2)); // return -1 (i.e., not found), The map is now [[1,1]]
    System.out.println("Error:"
                       + "e");
  }
}
