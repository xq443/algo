package BinarySearchTree;

public class CheckCompleteness {
  public boolean isCompleteTree(Node root) {
    if(root == null) return true;
    return dfs_isCompleteTree(root, 0, count(root));
  }

  public boolean dfs_isCompleteTree(Node node, int index, int count) {
    if(node == null) return true;
    if(index >= count) return false;

    return dfs_isCompleteTree(node.left, index * 2 + 1, count) &&
        dfs_isCompleteTree(node.right, index * 2 + 2, count);

  }
  public int count(Node root) {
    if(root == null) return 0;
    return 1 + count(root.left) + count(root.right);
  }

  public static void main(String[] args) {
    CheckCompleteness checkCompleteness = new CheckCompleteness();
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.right.right = new Node(7);
    System.out.println(checkCompleteness.isCompleteTree(root));
  }
}
