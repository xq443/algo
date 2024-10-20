package BinarySearchTree;

public class Subtree {
  public boolean isSubtree(Node root, Node subRoot) {
    if(root == null) return false;
    if(isSameTree(root, subRoot)) return true;
    return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
  }

  public boolean isSameTree(Node root, Node subRoot) {
    if(root == null && subRoot == null) return true;
    if(root == null || subRoot == null) return false;
    if(root.value != subRoot.value) return false;
    return isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right);
  }

  public static void main(String[] args) {
    Subtree st = new Subtree();
    Node root = new Node(1);
    root.left = new Node(3);
    root.right = new Node(2);

    Node subroot = new Node(1);
    subroot.left = new Node(3);
    subroot.right = new Node(2);

    System.out.println(st.isSubtree(root, subroot));
  }
}
