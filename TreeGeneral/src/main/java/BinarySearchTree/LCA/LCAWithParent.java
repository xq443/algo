package BinarySearchTree.LCA;

import java.util.HashSet;
import java.util.Set;

public class LCAWithParent {

  public static class NewNode{

    int value;
    NewNode left;
    NewNode right;
    NewNode parent;

    public NewNode(int value) {
      this.value = value;
    }
  }

  public NewNode lowestCommonAncestor(NewNode p, NewNode q) {
    Set<NewNode> set = new HashSet<>();
    set.add(p);
    set.add(q);
    NewNode currp = p;
    NewNode currq = q;

    while(currp != null || currq != null){
      if(currp != null){
        currp = currp.parent;
        if(set.contains(currp)) return currp;
        else set.add(currp);
      }
      if(currq != null){
        currq = currq.parent;
        if(set.contains(currq)) return currq;
        else set.add(currq);
      }
    }
    return null;
  }

  public static void main(String[] args) {
    LCAWithParent l = new LCAWithParent();
    NewNode root = new NewNode(3);
    // Construct the tree and set parent pointers
    root.left = new NewNode(5);
    root.right = new NewNode(1);
    root.left.parent = root;
    root.right.parent = root;

    root.left.left = new NewNode(6);
    root.left.left.parent = root.left;

    root.left.right = new NewNode(2);
    root.left.right.parent = root.left;

    root.left.right.left = new NewNode(7);
    root.left.right.left.parent = root.left.right;

    root.left.right.right = new NewNode(4);
    root.left.right.right.parent = root.left.right;

    root.right.left = new NewNode(0);
    root.right.left.parent = root.right;

    root.right.right = new NewNode(8);
    root.right.right.parent = root.right;

    System.out.println(l.lowestCommonAncestor(root.left, root.right).value);
  }
}
