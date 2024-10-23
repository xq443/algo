package LFU;

/**
 * Represents a doubly linked list used in the LFU (Least Frequently Used)
 * cache. This linked list maintains a reference to both the head and tail
 * nodes.
 */

public class DoublyLinkedList {
  Node head;
  Node tail;

  /**
   * Constructs a new doubly linked list with dummy head and tail nodes.
   * The head and tail nodes are initialized with key and value of -1.
   * The head node's next points to the tail node, and the tail node's prev
   * points to the head node.
   */
  public DoublyLinkedList() {
    // dummy end node
    this.head = new Node(-1, -1);
    this.tail = new Node(-1, -1);
    head.next = tail;
    tail.prev = head;
  }

  /**
   * Adds the specified node to the head of the doubly linked list.
   *
   * @param node The node to be added to the head.
   */
  public void addToHead(Node node) {
    node.prev = head;
    node.next = head.next;
    head.next.prev = node;
    head.next = node;
  }

  /**
   * Removes the specified node from the doubly linked list.
   *
   * @param node The node to be removed from the list.
   */
  public void removeNode(Node node) {
    node.next.prev = node.prev;
    node.prev.next = node.next;
  }

  /**
   * Removes and returns the tail node of the doubly linked list.
   * If the list is empty, returns null.
   *
   * @return The tail node of the list.
   */
  public Node removeTail() {
    if (isEmpty())
      return null;
    Node toRemove = tail.prev;
    removeNode(toRemove);
    return toRemove;
  }

  /**
   * Checks if the doubly linked list is empty.
   *
   * @return true if the list is empty, false otherwise.
   */
  public boolean isEmpty() { return head.next == tail; }
}
