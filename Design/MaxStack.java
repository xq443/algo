import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class MaxStack {

  /**
   * Design a max stack data structure
   * that supports the stack operations and supports finding the stack's maximum
   * element. O(1) for each top call and O(logn) for each other call.
   */
  public class ListNode {
    ListNode prev, next;
    int value;
    public ListNode(int value) { this.value = value; }
  }
  ListNode head;
  TreeMap<Integer, List<ListNode>> map;

  /**
   * Initializes the stack object.
   */
  public MaxStack() {
    this.head = new ListNode(0);
    this.head.prev = head;
    this.head.next = head;

    this.map = new TreeMap<>();
  }

  /**
   * Pushes element x onto the stack.
   * adding the new node at the front
   * @param x
   */
  void push(int x) {
    ListNode node = new ListNode(x);
    node.prev = head.prev;
    node.next = head;
    head.prev.next = node;
    head.prev = node;

    map.computeIfAbsent(x, k -> new LinkedList<>()).add(node);
  }

  /**
   * Removes the element on top of the stack and returns it.
   * @return
   */
  int pop() {
    if (head.prev == head)
      return -1; // empty
    ListNode popped = head.prev;
    delete(popped);

    map.get(popped.value).removeLast();
    if (map.get(popped.value).isEmpty()) {
      map.remove(popped.value);
    }

    return popped.value;
  }

  /**
   * Gets the element on the top of the stack without removing it.
   * @return
   */
  int top() {
    if (head.prev == head)
      return -1; // empty
    return head.prev.value;
  }

  /**
   * Retrieves the maximum element in the stack without removing it.
   * @return
   */
  int peekMax() { return map.lastKey(); }

  /**
   * Retrieves the maximum element in the stack and removes it.
   * If there is more than one maximum element, only remove the top-most one
   * @return
   */
  int popMax() {
    int max = peekMax();
    ListNode maxNode = map.get(max).removeLast();
    delete(maxNode);
    if (map.get(max).isEmpty()) {
      map.remove(max);
    }
    return max;
  }
  public void delete(ListNode node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  public static void main(String[] args) {
    MaxStack stk = new MaxStack();
    stk.push(5); // [5] the top of the stack and the maximum number is 5.
    stk.push(1); // [5, 1] the top of the stack is 1, but the maximum is 5.
    stk.push(5); // [5, 1, 5] the top of the stack is 5, which is also the
                 // maximum, because it is the top most one.
    System.out.println(
        stk.top()); // return 5, [5, 1, 5] the stack did not change.
    System.out.println(
        stk.popMax()); // return 5, [5, 1] the stack is changed now, and the top
                       // is different from the max.
    System.out.println(stk.top()); // return 1, [5, 1] the stack did not change.
    System.out.println(
        stk.peekMax());            // return 5, [5, 1] the stack did not change.
    System.out.println(stk.pop()); // return 1, [5] the top of the stack and the
                                   // max element is now 5.
    System.out.println(stk.top()); // return 5, [5] the stack did not change.
  }
}
