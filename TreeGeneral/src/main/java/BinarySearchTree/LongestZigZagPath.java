package BinarySearchTree;

public class LongestZigZagPath {
  int maxPathLength = 0;

  public int longestZigZag(Node root) {
    longestZigZagDfs(root, true, 0);
    longestZigZagDfs(root, false, 0);
    return maxPathLength;
  }

  public void longestZigZagDfs(Node curr, boolean isRightNext, int length) {
    if(curr == null) return;

    maxPathLength = Math.max(maxPathLength, length);

    if(isRightNext) {
      longestZigZagDfs(curr.right, false, length + 1);
      longestZigZagDfs(curr.left, true, 1); // Start a new path from left child
    } else {
      longestZigZagDfs(curr.left, true, length + 1);
      longestZigZagDfs(curr.right, false, 1);
    }
  }

  public static void main(String[] args) {
    LongestZigZagPath longestZigZagPath = new LongestZigZagPath();
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    //root.right.left = new Node(5);
    System.out.println(longestZigZagPath.longestZigZag(root));
  }
}
