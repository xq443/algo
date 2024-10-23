package LFU;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Least Frequently Used (LFU) cache.
 * The LFU cache supports the get and put operations.
 * When the cache reaches its capacity, it invalidates and removes the least
 * frequently and least recently used key before inserting a new item.
 */
public class LFU {
  public Map<Integer, Node> valueToNode;
  public Map<Integer, DoublyLinkedList> frequencyToLinkedList;
  public int minFrequency;
  public int capacity;

  /**
   * Initializes the object with the capacity of the data structure.
   * @param capacity
   */
  public LFU(int capacity) {
    this.frequencyToLinkedList = new HashMap<>();
    this.valueToNode = new HashMap<>();
    this.minFrequency = 0;
    this.capacity = capacity;
  }

  /**
   * Gets the value of the key if the key exists in the cache. Otherwise,
   * returns -1.
   * @param key
   * @return
   */
  public int get(int key) {
    if (!valueToNode.containsKey(key)) {
      return -1;
    }
    Node node = valueToNode.get(key);
    updateFrequency(node);
    return node.value;
  }

  /**
   * Update the value of the key if present, or inserts the key if not already
   * present. When the cache reaches its capacity, it should invalidate and
   * remove the least frequently used key before inserting a new item. For this
   * problem, when there is a tie (i.e., two or more keys with the same
   * frequency), the least recently used key would be invalidated.
   * @param key
   * @param value
   */
  public void put(int key, int value) {
    if (valueToNode.containsKey(key)) {
      Node newNode = valueToNode.get(key);
      newNode.value = value;
      updateFrequency(newNode);
    } else {
      if (valueToNode.size() == capacity) {
        DoublyLinkedList minFrequencyList =
            frequencyToLinkedList.get(minFrequency);
        Node toRemove = minFrequencyList.removeTail();
        updateFrequency(toRemove);
        // ensure remove the key whenever any node is removed
        valueToNode.remove(toRemove.key);
      }
      Node newNode = new Node(key, value);
      valueToNode.put(key, newNode);
      minFrequency = 1;
      if (!frequencyToLinkedList.containsKey(minFrequency)) {
        frequencyToLinkedList.put(minFrequency, new DoublyLinkedList());
      }
      frequencyToLinkedList.get(minFrequency).addToHead(newNode);
    }
  }

  /**
   * Updates the frequency of the given node and adjusts its position in the
   * linked list accordingly.
   *
   * @param node The node whose frequency needs to be updated.
   */
  public void updateFrequency(Node node) {
    int frequency = node.frequency;
    DoublyLinkedList list = frequencyToLinkedList.get(frequency);
    list.removeNode(node);

    // determine if this node was the only one with frequency minFrequency
    if (list.isEmpty() && frequency == minFrequency) {
      minFrequency++; // update the minFrequency
    }
    node.frequency++;
    if (!frequencyToLinkedList.containsKey(node.frequency)) {
      frequencyToLinkedList.put(node.frequency, new DoublyLinkedList());
    }
    frequencyToLinkedList.get(node.frequency).addToHead(node);
  }
}
