package BinarySearchTree.LCA;

import BinarySearchTree.Node;
import java.util.HashSet;
import java.util.Set;

public class LCANodeArray {
  /**
   * All Node.val are unique.
   * All nodes[i] will exist in the tree.
   * All nodes[i] are distinct.
   * @param root
   * @param nodes
   * @return
   */
  public Node lowestCommonAncestor(Node root, Node[] nodes) {
    if (root == null) return null;
    Set<Integer> set = new HashSet<>();
    for (Node node : nodes) {
      set.add(node.value);
    }
    return lowestCommonAncestor_dfs(root, set);
  }

  public Node lowestCommonAncestor_dfs(Node node, Set<Integer> set) {
    if (node == null) return null;
    if (set.contains(node.value)) return node;
    Node left = lowestCommonAncestor_dfs(node.left, set);
    Node right = lowestCommonAncestor_dfs(node.right, set);
    if (left != null && right != null) return node;
    return left != null ? left : right;
  }

  public static void main(String[] args) {
    LCANodeArray lca = new LCANodeArray();

    // Constructing the tree
    Node root = new Node(3);
    root.left = new Node(5);
    root.right = new Node(1);
    root.left.left = new Node(6);
    root.left.right = new Node(2);
    root.right.left = new Node(0);
    root.right.right = new Node(8);
    root.left.right.left = new Node(7);
    root.left.right.right = new Node(4);

    // Define the nodes array to find the LCA
    Node[] nodes = new Node[]{root.left.right.right, root.left.right.left}; // Nodes 4 and 7
    System.out.println(lca.lowestCommonAncestor(root, nodes).value);  // 2
  }
}
