package RedBlackTree;

public class RedBlackTree {
  private Node root;
  private final Node NIL; // Sentinel NIL node

  // Node class for Red-Black Tree
  class Node {
    int data;
    String color;
    Node left, right, parent;

    Node(int data) {
      this.data = data;
      this.color = "RED"; // New nodes are always red
      this.left = NIL;     // Leaf nodes point to NIL
      this.right = NIL;    // Leaf nodes point to NIL
      this.parent = null;
    }
  }

  // Constructor
  public RedBlackTree() {
    NIL = new Node(0); // Initialize the NIL node
    NIL.color = "BLACK";
    root = NIL;
  }

  // Insert method
  public void insert(int data) {
    Node newNode = new Node(data);
    newNode.left = NIL; // All leaf nodes point to NIL
    newNode.right = NIL; // All leaf nodes point to NIL

    Node parent = null;
    Node current = root;

    // BST insert to find the position for the new node
    while (current != NIL) {
      parent = current;
      if (newNode.data < current.data) {
        current = current.left;
      } else {
        current = current.right;
      }
    }

    // Set the parent of the new node
    newNode.parent = parent;

    if (parent == null) {
      // If the tree was empty, set the new node as the root
      root = newNode;
    } else if (newNode.data < parent.data) {
      parent.left = newNode;
    } else {
      parent.right = newNode;
    }

    // Fix Red-Black Tree properties after insertion
    fixInsert(newNode);
  }

  // Fixing Red-Black Tree properties after insertion
  private void fixInsert(Node k) {
    while (k != root && k.parent.color.equals("RED")) {
      if (k.parent == k.parent.parent.left) {
        Node u = k.parent.parent.right; // Uncle
        if (u.color.equals("RED")) {
          k.parent.color = "BLACK";
          u.color = "BLACK";
          k.parent.parent.color = "RED";
          k = k.parent.parent;
        } else {
          if (k == k.parent.right) {
            k = k.parent;
            leftRotate(k);
          }
          k.parent.color = "BLACK";
          k.parent.parent.color = "RED";
          rightRotate(k.parent.parent);
        }
      } else {
        Node u = k.parent.parent.left; // Uncle
        if (u.color.equals("RED")) {
          k.parent.color = "BLACK";
          u.color = "BLACK";
          k.parent.parent.color = "RED";
          k = k.parent.parent;
        } else {
          if (k == k.parent.left) {
            k = k.parent;
            rightRotate(k);
          }
          k.parent.color = "BLACK";
          k.parent.parent.color = "RED";
          leftRotate(k.parent.parent);
        }
      }
    }
    root.color = "BLACK"; // Ensure the root is always black
  }

  // Left rotation utility function
  private void leftRotate(Node x) {
    Node y = x.right;
    x.right = y.left;
    if (y.left != NIL) {
      y.left.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null) {
      root = y;
    } else if (x == x.parent.left) {
      x.parent.left = y;
    } else {
      x.parent.right = y;
    }
    y.left = x;
    x.parent = y;
  }

  // Right rotation utility function
  private void rightRotate(Node x) {
    Node y = x.left;
    x.left = y.right;
    if (y.right != NIL) {
      y.right.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null) {
      root = y;
    } else if (x == x.parent.right) {
      x.parent.right = y;
    } else {
      x.parent.left = y;
    }
    y.right = x;
    x.parent = y;
  }

  // Inorder traversal for testing
  public void inorder() {
    inorderHelper(this.root);
  }

  private void inorderHelper(Node node) {
    if (node != NIL) {
      inorderHelper(node.left);
      System.out.print(node.data + " ");
      inorderHelper(node.right);
    }
  }

  // Main function to test the tree
  public static void main(String[] args) {
    RedBlackTree rbt = new RedBlackTree();

    // Inserting elements
    rbt.insert(30);
    rbt.insert(20);
    rbt.insert(40);
    rbt.insert(10);

    // Inorder traversal
    System.out.println("Inorder traversal:");
    rbt.inorder();
  }
}
