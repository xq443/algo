package BinarySearchTree.LCA;

import BinarySearchTree.Node;

public class LCAWithDeepestLeaves {
  public Node lcaDeepestLeaves(Node root) {
    if (root == null) return null;
    int left = maxHeight(root.left);
    int right = maxHeight(root.right);
    if (left > right) return lcaDeepestLeaves(root.left);
    else if (right > left) return lcaDeepestLeaves(root.right);
    else return root;
  }

  public int maxHeight(Node root) {
    if (root == null) return 0;
    return 1 + Math.max(maxHeight(root.left), maxHeight(root.right));
  }

  public static void main(String[] args) {
    LCAWithDeepestLeaves lca = new LCAWithDeepestLeaves();
    Node root = new Node(3);
    root.left = new Node(5);
    root.right = new Node(1);
    root.left.left = new Node(6);
    root.left.right = new Node(2);
    root.left.right.left = new Node(7);
    root.left.right.right = new Node(4);
    root.right.left = new Node(0);
    root.right.right = new Node(8);
    System.out.println(lca.lcaDeepestLeaves(root).value);
  }

}
