package BinarySearchTree.LCA;


import BinarySearchTree.Node;

public class LCAFound {
  boolean found = false;
  public Node lowestCommonAncestor(Node root, Node p, Node q) {
    if(root == null) return root;
    Node ret = lowestCommonAncestor_dfs(root, p, q);
    return found ? ret : null;
  }

  public Node lowestCommonAncestor_dfs(Node node, Node p, Node q) {
    if(node == null) return null;

    Node left = lowestCommonAncestor_dfs(node.left, p, q);
    Node right = lowestCommonAncestor_dfs(node.right, p, q);

    int count = 0;
    if(node == p  || node == q) count++;
    if(left != null) count++;
    if(right != null) count++;
    if(count == 2) found = true;

    if((left != null && right != null) || node == p || node == q ) return node;
    return left != null ? left : right;
  }

  public static void main(String[] args) {
    LCAFound lca = new LCAFound();
    Node root = new Node(3);
    root.left = new Node(5);
    root.right = new Node(1);
    root.left.left = new Node(6);
    root.left.right = new Node(2);
    root.right.left = new Node(0);
    root.right.right = new Node(8);
    root.left.right.left = new Node(7);
    root.left.right.right = new Node(4);
    System.out.println(lca.lowestCommonAncestor(root, root.right, root.left.right).value);
  }

}
