package Tree;

import java.util.ArrayList;
import java.util.List;

public class ConvertSortedListToBinarySearchTree {

  /**
   * Given the head of a singly linked list where elements are sorted in ascending order,
   * convert it to a height-balanced binary search tree.
   * @param head
   * @return
   */
  public TreeNode sortedListToBST(ListNode head) {
    if(head == null) return null;
    List<Integer> list = new ArrayList<>();
    while(head != null) {
      list.add(head.value);
      head = head.next;
    }
    return buildTree(list, 0, list.size() - 1);
  }

  private TreeNode buildTree(List<Integer> list, int left, int right) {
    if(left > right) return null;
    int mid = left + (right - left) / 2;
    TreeNode node = new TreeNode(list.get(mid));
    node.left = buildTree(list, left, mid - 1);
    node.right = buildTree(list, mid + 1, right);
    return node;
  }

  public static void main(String[] args) {
    ConvertSortedListToBinarySearchTree convertSortedListToBinarySearchTree =
        new ConvertSortedListToBinarySearchTree();

    ListNode head = new ListNode(-10);
    head.next = new ListNode(-3);
    head.next.next = new ListNode(0);
    head.next.next.next = new ListNode(5);
    head.next.next.next.next = new ListNode(9);

    TreeNode root = convertSortedListToBinarySearchTree.sortedListToBST(head);
    System.out.println(root.right.val);
  }
}
