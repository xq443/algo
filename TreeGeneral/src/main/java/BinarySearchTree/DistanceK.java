package BinarySearchTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class DistanceK {
  List<Integer> ret = new ArrayList<>();
  Map<Node, List<Node>> map = new HashMap<>();
  public List<Integer> distanceK(Node root, Node target, int K) {
    if(root == null) return ret;
    buildMap(root, null);

    if(!map.containsKey(target)) return ret;

    // bfs
    Queue<Node> queue = new LinkedList<>();
    Set<Node> visited = new HashSet<>();
    queue.add(target);
    visited.add(target);
    while(!queue.isEmpty()) {
      int size = queue.size();
      if(K == 0) {
        for(int i = 0; i < size; i++) {
          ret.add(Objects.requireNonNull(queue.poll()).value);
        }
        return ret;
      }
      for(int i = 0; i < size; i++) {
        Node curr = queue.poll();
        if(map.containsKey(curr)) {
          for(Node next : map.get(curr)) {
            if(visited.contains(next)) continue;
            visited.add(next);
            queue.add(next);
          }
        }
      }
      K--;
    }
    return ret;
  }

  public void buildMap(Node node, Node parent) {
    if (node == null) return;  // Add this line
    if(!map.containsKey(node)) {
      map.put(node, new ArrayList<>());
      if(parent != null) {
        map.get(node).add(parent);
        map.get(parent).add(node);
      }
      buildMap(node.left, node);
      buildMap(node.right, node);
    }
  }

  public static void main(String[] args) {
    DistanceK dk = new DistanceK();
    Node root = new Node(3);
    root.left = new Node(5);
    root.right = new Node(1);
    root.left.left = new Node(6);
    root.left.right = new Node(2);
    root.right.left = new Node(0);
    root.right.right = new Node(8);
    root.left.right.left = new Node(7);
    root.left.right.right = new Node(4);

    System.out.println(dk.distanceK(root, root, 2));
  }
}
