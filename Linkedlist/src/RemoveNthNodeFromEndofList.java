public class RemoveNthNodeFromEndofList {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode prev = dummy;
    ListNode curr = dummy;
    for (int i = 0; i < n; i++) {
      curr = curr.next;
    }

    while (curr.next != null) {
      prev = prev.next;
      curr = curr.next;
    }
    prev.next = prev.next.next;
    return dummy.next;
  }

  public static void main(String[] args) {
    RemoveNthNodeFromEndofList removeNthNodeFromEndofList = new RemoveNthNodeFromEndofList();
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    removeNthNodeFromEndofList.removeNthFromEnd(head, 2);
    ListNode curr = head;
    while (curr != null) {
      System.out.println(curr.val);
      curr = curr.next;
    }
  }
}
