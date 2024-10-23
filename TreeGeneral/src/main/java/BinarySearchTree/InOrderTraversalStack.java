package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InOrderTraversalStack {
  public List<Integer>  inOrderTraversal(Node root) {
    if (root == null) return new ArrayList<>();
    Stack<Node> stack = new Stack<>();
    List<Integer> ret = new ArrayList<>();
    Node current = root;
    while (current != null) {
      stack.push(current);
      current = current.left;
    }

    while (!stack.isEmpty()) {
      current = stack.pop();
      ret.add(current.value);
      Node next = current.right;
      while(next != null) {
        stack.push(next);
        next = next.left;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    InOrderTraversalStack in = new InOrderTraversalStack();
    Node root = new Node(2);
    root.left = new Node(1);
    root.right = new Node(3);
    System.out.println(in.inOrderTraversal(root));
  }
}
