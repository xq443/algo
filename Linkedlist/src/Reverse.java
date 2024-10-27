public class Reverse {
  public ListNode reverseList_iterate(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode temp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = temp;
    }
    return prev;
  }

  public ListNode reverseList_recursive(ListNode head) {
    return recursiveReverse(head, null);
  }

  public ListNode recursiveReverse(ListNode curr, ListNode prev) {
    if (curr == null) return prev;

    ListNode temp = curr.next;
    curr.next = prev;
    prev = curr;
    curr = temp;

    return recursiveReverse(curr, prev);
  }

  public static void main(String[] args) {
    Reverse reverse = new Reverse();
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    System.out.println(reverse.reverseList_iterate(head).val);
    System.out.println(reverse.reverseList_recursive(head).val);
  }
}
