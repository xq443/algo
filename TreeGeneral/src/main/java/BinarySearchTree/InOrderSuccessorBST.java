package BinarySearchTree;

public class InOrderSuccessorBST {
  public Node inorderSuccessor_iterative(Node root, Node p) {
    if (root == null) return null;
    Node curr = root;

    Node successor = null;
    while (curr != null) {
      if(curr.value > p.value) {
        successor = curr;
        curr = curr.left;
      } else {
        curr = curr.right;
      }
    }
    return successor;
  }

  public Node inorderSuccessor_recursive(Node root, Node p) {
    if(root == null) return root;
    if(root.value > p.value) {
      Node curr = inorderSuccessor_recursive(root.left, p);
      if(curr == null) return root;
      return curr;
    } else{
      return inorderSuccessor_recursive(root.right, p);
    }
  }

  public static void main(String[] args) {
    InOrderSuccessorBST inorderSuccessorBST = new InOrderSuccessorBST();
    Node root = new Node(2);
    root.left = new Node(1);
    root.right = new Node(3);
    System.out.println(inorderSuccessorBST.inorderSuccessor_iterative(root, root.left).value);
    System.out.println(inorderSuccessorBST.inorderSuccessor_recursive(root, root.left).value);
  }
}
