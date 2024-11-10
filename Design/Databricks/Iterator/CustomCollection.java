package Databricks.Iterator;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class CustomCollection<T> {
  private static class Node<T> {
    T value;
    Node<T> prev;
    Node<T> next;
    boolean isDeleted;

    Node(T value) {
      this.value = value;
      this.isDeleted = false;
    }
  }

  private Node<T> head;
  private Node<T> tail;
  private HashMap<T, Node<T>> map;

  public CustomCollection() {
    this.head = null;
    this.tail = null;
    this.map = new HashMap<>();
  }

  public void add(T value) {
    Node<T> newNode = new Node<>(value);
    if (head == null) {
      head = tail = newNode;
    } else {
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
    }
    map.put(value, newNode);
  }

  public void remove(T value) {
    Node<T> node = map.get(value);
    if (node != null) {
      node.isDeleted = true;
      map.remove(value);
    }
  }

  public CustomIterator iterator() {
    return new CustomIterator(head);
  }

  public class CustomIterator {
    private Node<T> current;

    CustomIterator(Node<T> head) {
      this.current = head;
    }

    public boolean hasNext() {
      while (current != null && current.isDeleted) {
        System.out.println("Skipping deleted node: " + current.value);
        current = current.next;
      }
      return current != null;
    }

    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      T value = current.value;
      current = current.next;
      return value;
    }
  }

  public void main(String[] args) {
    CustomCollection<Integer> collection = new CustomCollection<>();
    collection.add(1);
    collection.add(2);
    collection.add(3);

    CustomIterator iterator = (CustomIterator) collection.iterator();

    collection.remove(2);

    while (iterator.hasNext()) {
      System.out.println("Iterator value: " + iterator.next());
    }

    // Output should be:
    // Skipping deleted node: 2
    // Iterator value: 3
    // Iterator value: 1
  }
}
