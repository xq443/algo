package BinarySearchTree.LCA;

import BinarySearchTree.Node;

public class LCA {
  // O(n) O(n)
  public Node lowestCommonAncestor(Node root, Node p, Node q) {
    if(root == null || root == p || root == q) return root;
    Node left = lowestCommonAncestor(root.left, p, q);
    Node right = lowestCommonAncestor(root.right, p, q);
    if(left != null && right != null) return root;
    return left != null ? left : right;
  }

  // O(lgn) O(lgn)
  public Node lowestCommonAncestor_recursive_bst(Node root, Node p, Node q) {
    if(root == null) return root;
    if(root.value > p.value && root.value > q.value) {
      return lowestCommonAncestor_recursive_bst(root.left, p, q);
    }
    else if(root.value < p.value && root.value < q.value) {
      return lowestCommonAncestor_recursive_bst(root.right, p, q);
    }
    else
      return root;
  }

  public static void main(String[] args) {
    LCA lca = new LCA();
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

    Node root2 = new Node(2);
    root2.left = new Node( 1);
    root2.right = new Node(3);
    System.out.println(lca.lowestCommonAncestor_recursive_bst(root2, root2.left, root2.right).value);  // LCA of 5 and 4 in a BST
  }
}
