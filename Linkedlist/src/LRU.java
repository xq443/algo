import java.util.HashMap;
import java.util.Map;

public class LRU {
  public static class Node{
    int key;
    int value;
    Node next;
    Node prev;
  }

  Map<Integer, Node> map;
  Node head;
  int capacity;

  public LRU(int capacity) {
    this.capacity = capacity;
    this.map = new HashMap<>(capacity);
    this.head = new Node();
    this.head.next = this.head;
    this.head.prev = this.head;
  }

  public int get(int key) {
    if(map.containsKey(key)) {
      Node node = map.get(key);
      moveToHead(node);
      return node.value;
    }
    return -1;
  }

  public void put(int key, int value) {
    if(map.containsKey(key)) {
      Node node = map.get(key);
      node.value = value;
      moveToHead(node);
    } else{
      if(capacity <= map.size()) {
        Node removeNode =  removeTail();
        map.remove(removeNode.key);
      }
      Node node = new Node();
      node.key = key;
      node.value = value;
      moveToHeadNext(node);
      map.put(key, node);
    }
  }

  public void moveToHead(Node node) {
    removeNode(node);
    moveToHeadNext(node);
  }

  public void removeNode(Node node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  public void moveToHeadNext(Node node) {
    node.prev = head;
    node.next = head.next;
    head.next.prev = node;
    head.next = node;
  }

  public Node removeTail() {
    Node tail = head.prev;
    removeNode(tail);
    return tail;
  }

  public static void main(String[] args) {
    int capacity = 2;
    LRU lRUCache = new LRU(capacity);
    lRUCache.put(1, 1);                  // cache is {1=1}
    lRUCache.put(2, 2);                  // cache is {1=1, 2=2}
    System.out.println(lRUCache.get(1)); // return 1
    lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    System.out.println(lRUCache.get(2)); // returns -1 (not found)
    lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    System.out.println(lRUCache.get(1)); // return -1 (not found)
    System.out.println(lRUCache.get(3)); // return 3
    System.out.println(lRUCache.get(4)); // return 4
  }
}

// get and put each run in O(1)
