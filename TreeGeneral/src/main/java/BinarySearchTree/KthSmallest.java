package BinarySearchTree;


public class KthSmallest {
  int ret = 0;
  int count = 0;
  public int kthSmallest(Node root, int k) {
    inorder(root, k);
    return (ret > 0) ? ret : -1;
  }

  public void inorder(Node node, int k) {
    if(node == null) return;
    inorder(node.left, k);
    count++;
    if(count == k) {
      ret = node.value;
      return;
    }
    inorder(node.right, k);
  }

  public static void main(String[] args) {
    KthSmallest st = new KthSmallest();
    Node root = new Node(1);
    root.left = new Node(3);
    root.right = new Node(2);
    System.out.println(st.kthSmallest(root, 4));
  }
}
