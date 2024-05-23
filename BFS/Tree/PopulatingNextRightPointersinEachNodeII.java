public class PopulatingNextRightPointersinEachNodeII {
  public static class Node{
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node(int val) {
      this.val = val;
    }
  }

  /**
   * Given a binary tree
   *
   * struct Node {
   *   int val;
   *   Node *left;
   *   Node *right;
   *   Node *next;
   * }
   * Populate each next pointer to point to its next right node.
   * If there is no next right node, the next pointer should be set to NULL.
   * Initially, all next pointers are set to NULL.
   */

  public Node connect(Node root) {
    if(root == null) return null;

    Node leftmost = root;

    while (leftmost != null) {
      Node curr = leftmost;
      Node prev = null;
      Node nextLevelStart = null;

      while (curr != null) {
        if(curr.left != null) {
          if(prev == null) {
            nextLevelStart = curr.left;
          } else{
            prev.next = curr.left;
          }
          prev = curr.left;
        }

        if(curr.right != null) {
          if(prev == null) {
            nextLevelStart = curr.right;
          } else{
            prev.next = curr.right;
          }
          prev = curr.right;
        }
        curr = curr.next;
      }
      leftmost = nextLevelStart;
    }
    return root;
  }

  public static void main(String[] args) {
    PopulatingNextRightPointersinEachNodeII populatingNextRightPointersinEachNodeII = new PopulatingNextRightPointersinEachNodeII();

    // create new binary tree
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.right.right = new Node(7);

    populatingNextRightPointersinEachNodeII.connect(root);

    // Print the tree nodes in level order using next pointers
    Node level = root;
    while (level != null) {
      Node current = level;
      while (current != null) {
        System.out.print(current.val + " ");
        current = current.next;
      }
      System.out.print("# "); // Mark the end of a level

      // Move to the next level
      Node nextLevel = null;
      while (level != null && nextLevel == null) {
        if (level.left != null) {
          nextLevel = level.left;
        } else if (level.right != null) {
          nextLevel = level.right;
        } else {
          level = level.next;
        }
      }
      level = nextLevel;
    }
  }
}
