package BinarySearchTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {
  public List<Integer> rightSideView_bfs(Node root) {
    List<Integer> ret = new ArrayList<>();
    // bfs
    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);
    while(!queue.isEmpty()) {
      int size = queue.size();
      for(int i = 0; i < size; i++) {
        Node curr = queue.poll();
        if(i == 0) ret.add(curr.value);
        assert curr != null;
        if(curr.right != null) queue.offer(curr.right);
        if(curr.left != null) queue.offer(curr.left);
      }
    }
    return ret;
  }

  public List<Integer> rightSideView(Node root) {
    List<Integer> ret = new ArrayList<>();
    rightSideView_dfs(root, 0, ret);
    return ret;
  }

  public void rightSideView_dfs(Node node, int level, List<Integer> ret) {
    if(node == null) return;
    if(ret.size() == level) ret.add(node.value);
    rightSideView_dfs(node.right, level + 1, ret);
    rightSideView_dfs(node.left, level + 1, ret);
  }

  public static void main(String[] args) {
    BinaryTreeRightSideView binaryRightView = new BinaryTreeRightSideView();
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.right.right = new Node(7);
    System.out.println(binaryRightView.rightSideView_bfs(root));
    System.out.println(binaryRightView.rightSideView(root));
  }
}
