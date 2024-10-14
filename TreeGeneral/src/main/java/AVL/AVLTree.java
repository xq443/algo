package AVL;

/**
 * An AVL tree is a binary search tree, where the heights of the left and right subtrees of each node differ by at most 1.
 * Checks if a given binary search tree is an AVL tree.
 * returns True if root is a root node of an AVL tree, and returns False if not.
 * Assume that root is a root node of a valid binary search tree.
 * Note that root can be None, which means the input is an empty tree. You may define one or more helper functions.
 */
public class AVLTree {
  public boolean isAVLTree(Node root) {
    if(root == null) return true;
    int leftHeight = AVLheight(root.left);
    int rightHeight = AVLheight(root.right);
    return isAVLTree(root.right) && isAVLTree(root.left) && Math.abs(leftHeight - rightHeight) <= 1;
  }


  public int AVLheight(Node root) {
    if(root == null) return 0;
    return Math.max(AVLheight(root.left), AVLheight(root.right)) + 1;
  }

  public static void main(String[] args) {
    AVLTree tree = new AVLTree();
    Node root = new Node(10);
    root.left = new Node(5);
    root.right = new Node(15);
    root.left.left = new Node(2);
    root.left.right = new Node(7);
    System.out.println(tree.isAVLTree(root)); // Expected output: true


    Node newRoot = new Node(10);
    newRoot.left = new Node(5);
    newRoot.left.left = new Node(2);
    newRoot.left.left.left = new Node(1);
    System.out.println(tree.isAVLTree(newRoot)); // Expected output: false
  }
}
