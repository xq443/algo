package Databricks.Iterator;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class CustomCollection<T> {

  // Inner class to represent a Node in the doubly linked list
  private static class Node<T> {
    T value;          // The value held by the node
    Node<T> prev;     // Reference to the previous node
    Node<T> next;     // Reference to the next node
    boolean isDeleted; // Flag to mark deleted nodes

    // Constructor to initialize the node with a value
    Node(T value) {
      this.value = value;
      this.isDeleted = false; // Initially not deleted
    }
  }

  private Node<T> head;          // Head of the doubly linked list
  private Node<T> tail;          // Tail of the doubly linked list
  private HashMap<T, Node<T>> map; // HashMap to quickly locate nodes by value

  // Constructor to initialize the collection
  public CustomCollection() {
    this.head = null;           // No head initially
    this.tail = null;           // No tail initially
    this.map = new HashMap<>(); // Initialize empty map
  }

  // Method to add a value to the collection
  public void add(T value) {
    Node<T> newNode = new Node<>(value); // Create a new node with the value

    if (head == null) { // If the collection is empty
      head = tail = newNode; // Set both head and tail to the new node
    } else {
      newNode.next = head; // New node points to the current head
      head.prev = newNode; // Current head points back to the new node
      head = newNode;      // Update head to the new node
    }
    map.put(value, newNode); // Add the node to the map for fast lookup
  }

  // Method to remove a value from the collection
  public void remove(T value) {
    Node<T> node = map.get(value); // Retrieve the node using the value

    if (node != null) { // If the node exists
      node.isDeleted = true; // Mark it as deleted
      map.remove(value);     // Remove it from the map for future lookups
    }
  }

  // Method to get an iterator for the collection
  public CustomIterator iterator() {
    return new CustomIterator(head); // Return an iterator starting from the head
  }

  // Inner class representing the custom iterator
  public class CustomIterator {
    private Node<T> current; // Current node for iteration

    // Constructor to initialize the iterator with the head node
    CustomIterator(Node<T> head) {
      this.current = head;
    }

    // Method to check if there are more elements to iterate
    public boolean hasNext() {
      // Skip deleted nodes
      while (current != null && current.isDeleted) {
        System.out.println("Skipping deleted node: " + current.value);
        current = current.next; // Move to the next node
      }
      return current != null; // Return true if the current node is not null
    }

    // Method to get the next value in the iteration
    public T next() {
      if (!hasNext()) { // If there are no more elements
        throw new NoSuchElementException(); // Throw an exception
      }
      T value = current.value; // Retrieve the current value
      current = current.next;  // Move to the next node
      return value;            // Return the value
    }
  }

  // Main method to test the CustomCollection
  public void main(String[] args) {
    CustomCollection<Integer> collection = new CustomCollection<>(); // Create a new collection

    // Add elements to the collection
    collection.add(1);
    collection.add(2);
    collection.add(3);

    CustomIterator iterator = (CustomIterator) collection.iterator(); // Get an iterator

    // Remove element 2 from the collection
    collection.remove(2);

    // Iterate through the collection and print the values
    while (iterator.hasNext()) {
      System.out.println("Iterator value: " + iterator.next());
    }

    // Expected output:
    // Skipping deleted node: 2
    // Iterator value: 3
    // Iterator value: 1
  }
}
//Time Complexity:
//add(T value): O(1)
//remove(T value): O(1) (average case)
//hasNext() and next(): O(1) per call
//Full iteration: O(n)
//Space Complexity:
//Collection space: O(n)
//Iterator space: O(1)