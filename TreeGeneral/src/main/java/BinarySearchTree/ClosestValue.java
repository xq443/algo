package BinarySearchTree;

public class ClosestValue {
  public int closestValue(Node root, double target) {
    int curr = root.value;
    Node next = (curr < target) ? root.right : root.left;
    if(next == null) return curr;
    int temp = closestValue(next, target);

    if(Math.abs(temp - target) < Math.abs(curr - target)) return temp;
    else if(Math.abs(temp - target) > Math.abs(curr - target)) return curr;
    else return Math.min(temp, curr);
  }

  public static void main(String[] args) {
    ClosestValue closestValue = new ClosestValue();
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.right.right = new Node(7);
    System.out.println(closestValue.closestValue(root, 3.5));
    System.out.println(closestValue.closestValue(root, 6.4));
  }
}

//O(h) O(h)