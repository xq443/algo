import java.util.HashMap;
import java.util.Map;

public class LRUCache {
  public static class Node{
    int key;
    int value;
    Node prev, next;
    public Node() {
    }
  }

  Node head;
  Map<Integer, Node> map;
  int capacity;
  public LRUCache(int capacity){
    this.map = new HashMap<>();
    this.head = new Node();
    head.prev = head;
    head.next = head;
    this.capacity = capacity;
  }

  public int get(int key) {
    if(map.containsKey(key)) {
      Node node = map.get(key);
      moveToHead(node);
      return node.value;
    }
    return -1;
  }

  public void put(int key, int value){
    if(map.containsKey(key)) {
      // refresh
      Node node = map.get(key);
      node.value = value;
      moveToHead(node);
    }else{
      // new added
      if(capacity <= map.size()) {
        Node nodeToRemove = removeTail();
        map.remove(nodeToRemove.key); // remove key
      }
      Node node = new Node();
      node.value = value;
      node.key = key;
      map.put(key, node);
      addToHeadNext(node);
    }

  }
  public void moveToHead(Node node) {
    removeNode(node);
    addToHeadNext(node);
  }
  public void removeNode(Node node){
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }
  public void addToHeadNext(Node node){
    node.prev = head;
    node.next = head.next;
    head.next.prev = node;
    head.next = node;
  }
  public Node removeTail() {
    Node tail = head.prev;
    removeNode(head.prev);
    return tail;
  }

  public static void main(String[] args) {
    int capacity = 2;
    LRUCache lRUCache = new LRUCache(capacity);
    lRUCache.put(1, 1); // cache is {1=1}
    lRUCache.put(2, 2); // cache is {1=1, 2=2}
    System.out.println(lRUCache.get(1));   // return 1
    lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    System.out.println(lRUCache.get(2));     // returns -1 (not found)
    lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    System.out.println(lRUCache.get(1));       // return -1 (not found)
    System.out.println(lRUCache.get(3));       // return 3
    System.out.println(lRUCache.get(4));       // return 4
  }
}
