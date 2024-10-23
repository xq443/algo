package BinarySearchTree;

import java.util.Stack;

public class InOrderTraversalStackSwapNodes {
  public void inorderTraversalSwap(Node root) {
    Stack<Node> stack = new Stack<>();
    Node current = root;
    Node pre = null, first = null, second = null;

    while (current != null) {
      stack.push(current);
      current = current.left;
    }

    while (!stack.isEmpty()) {
      current = stack.pop();
      if(pre != null && pre.value > current.value) {
        first = pre;
        second = current;
      }
      Node next = current.right;
      while (next != null) {
        stack.push(next);
        next = next.left;
      }
      pre = current;
    }

    // Swap the values of the two identified nodes if needed
    if (first != null && second != null) {
      int temp = first.value;
      first.value = second.value;
      second.value = temp;
    }
  }

  public static void main(String[] args) {
    Node root = new Node(3);
    root.left = new Node(1);
    root.right = new Node(4);
    root.right.left = new Node(2);

    System.out.println("Before swap correction:");
    printInOrder(root);

    InOrderTraversalStackSwapNodes tree = new InOrderTraversalStackSwapNodes();
    tree.inorderTraversalSwap(root);

    System.out.println("\nAfter swap correction:");
    printInOrder(root);
  }

  // print in-order traversal
  public static void printInOrder(Node root) {
    if (root == null) return;
    printInOrder(root.left);
    System.out.print(root.value + " ");
    printInOrder(root.right);
  }
}
