package BinarySearchTree;


public class Validate {
  public boolean isValidBST(Node root) {
    return isValidBST_dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public boolean isValidBST_dfs(Node node, int min, int max) {
    if(node == null) return true;
    if(node.value >= max || node.value <= min) return false;
    return isValidBST_dfs(node.left, min, node.value)
          && isValidBST_dfs(node.right, node.value, max);
  }

  public static void main(String[] args) {
    Validate v = new Validate();
    Node root = new Node(1);
    root.left = new Node(3);
    root.right = new Node(2);
    System.out.println(v.isValidBST(root));
  }
}
