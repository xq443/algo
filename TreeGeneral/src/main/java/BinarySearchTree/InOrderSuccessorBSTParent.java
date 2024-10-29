package BinarySearchTree;

public class InOrderSuccessorBSTParent {
  public Node inorderSuccessor(Node node) {
    if (node == null) return null;
    // case 1: has right child
    if(node.right != null) {
      Node temp = node.right;
      while(temp.left != null) {
        temp = temp.left;
      }
      return temp;
    }

    // case 2: does not have right child
    Node parent = node.parent;
    while(parent != null) {
      if(parent.left == node) {
        return parent;
      }
      node = parent;
      parent = node.parent;
    }
    return parent;
  }

  public static void main(String[] args) {
    InOrderSuccessorBSTParent i =  new InOrderSuccessorBSTParent();
    Node root = new Node(2);
    root.left = new Node(1);
    root.right = new Node(3);
    System.out.println(i.inorderSuccessor(root).value);
  }
}
