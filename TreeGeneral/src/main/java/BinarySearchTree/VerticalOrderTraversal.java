package BinarySearchTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class VerticalOrderTraversal {
  int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
  public List<List<Integer>> verticalOrder(Node root) {
    List<List<Integer>> ret = new ArrayList<>();
    if(root == null) return ret;
    findBoundary(root, 0);

    for(int i = min; i <= max; i++) {
      ret.add(new ArrayList<>());
    }

    Queue<Node> queue = new LinkedList<>();
    Queue<Integer> idx = new LinkedList<>();
    queue.add(root);
    idx.add(-min); // corresponding to the root

    while(!queue.isEmpty()) {
      int size = queue.size();
      for(int i = 0; i < size; i++) {
        Node currNode = queue.poll();
        int currIdx = idx.poll();
        ret.get(currIdx).add(currNode.value);
        if(currNode.left != null)  {
          queue.add(currNode.left);
          idx.add(currIdx - 1);
        }
        if(currNode.right != null)  {
          queue.add(currNode.right);
          idx.add(currIdx + 1);
        }
      }
    }
    return ret;
  }

  public void findBoundary(Node node, int idx) {
    if(node == null) return;
    findBoundary(node.left, idx - 1);
    min = Math.min(min, idx);
    findBoundary(node.right, idx + 1);
    max = Math.max(max, idx);
  }

  public static void main(String[] args) {
    VerticalOrderTraversal vt = new VerticalOrderTraversal();
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.left.right = new Node(5);
    root.left.left.right.right = new Node(6);
    root.right.left = new Node(9);
    root.right.right = new Node(11);
    root.right.left.left = new Node(10);
    System.out.println(vt.verticalOrder(root));
  }
}
