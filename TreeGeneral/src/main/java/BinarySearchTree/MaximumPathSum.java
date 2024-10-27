package BinarySearchTree;


public class MaximumPathSum {
  int ret = Integer.MIN_VALUE;
  public int maxPathSum(Node root) {
    if (root == null) return 0;
    maxPathSumDfs(root);
    return ret;
  }

  public int maxPathSumDfs(Node node) {
    if (node == null) return 0;
    int left = maxPathSumDfs(node.left);
    int right = maxPathSumDfs(node.right);
    ret = Math.max(ret, left + right + node.value);
    return Math.max(left, right) + node.value;
  }

  public static void main(String[] args) {
    MaximumPathSum  m = new MaximumPathSum();
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    System.out.println(m.maxPathSum(root));
  }
}
