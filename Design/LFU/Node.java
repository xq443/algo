package LFU;

public class Node {
  /**
   * Represents a node in the LFU (Least Frequently Used) cache.
   * Each node stores a key-value pair along with its frequency of access.
   */
  public int key;
  public int value;
  public int frequency;
  public Node prev;
  public Node next;

  /**
   * Constructs a new node with the specified key and value.
   *
   * @param key The key associated with the node.
   * @param value The value associated with the node.
   */
  public Node(int key, int value) {
    this.key = key;
    this.value = value;
    this.frequency = 1;
  }
}
