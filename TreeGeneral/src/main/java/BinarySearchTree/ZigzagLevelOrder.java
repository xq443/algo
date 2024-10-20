package BinarySearchTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigzagLevelOrder {
  public List<List<Integer>> zigzagLevelOrder(Node root) {
    List<List<Integer>> ret = new ArrayList<>();
    if(root == null) return ret;
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    while(!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> sub = new ArrayList<>();
      for(int i = 0; i < size; i++) {
        Node curr = queue.poll();
        if(ret.size() % 2 == 0) {
          sub.add(curr.value);
        } else
          sub.addFirst(curr.value);
        if(curr.left != null) queue.add(curr.left);
        if(curr.right != null) queue.add(curr.right);
      }
      ret.add(sub);
    }
    return ret;
  }

  public List<List<Integer>> zigzagLevelOrder_dfs(Node root) {
    List<List<Integer>> ret = new ArrayList<>();
    if(root == null) return ret;
    zigzag_dfs(root, ret, 0);
    return ret;
  }

  public void zigzag_dfs(Node node, List<List<Integer>> ret, int level) {
    if(node == null) return;
    if(ret.size() == level) {
      List<Integer> sub = new ArrayList<>();
      ret.add(sub);
    }
    if(level % 2 == 0) {
      ret.get(level).add(node.value);
    } else ret.get(level).addFirst(node.value);

    zigzag_dfs(node.left, ret, level + 1);
    zigzag_dfs(node.right, ret, level + 1);
  }


  public static void main(String[] args) {
    ZigzagLevelOrder z = new ZigzagLevelOrder();
    Node root = new Node(3);
    root.left = new Node(9);
    root.right = new Node(20);
    root.right.left = new Node(15);
    root.right.right = new Node(7);
    System.out.println(z.zigzagLevelOrder(root));
    System.out.println(z.zigzagLevelOrder_dfs(root));
  }
}
