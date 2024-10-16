package Tree;

import java.util.ArrayList;
import java.util.List;

public class BoundaryofBinaryTree {
  List<Integer> ret = new ArrayList<>();
  public List<Integer> boundaryOfBinaryTree(TreeNode root){
    if(root.left == null && root.right == null) {
      ret.add(root.val);
      return ret;
    }
    ret.add(root.val);
    getLeftBoundary(root.left);
    getLeaves(root);
    getRightBoundary(root.right);
    return ret;
  }

  private void getLeftBoundary(TreeNode node) {
    if(node == null || (node.left == null && node.right == null)) return;
    ret.add(node.val);
    if(node.left != null) {
      getLeftBoundary(node.left);
    } else getLeftBoundary(node.right);
  }

  private void getLeaves(TreeNode node) {
    if(node == null) return;
    if(node.left == null && node.right == null) ret.add(node.val);
    assert node.left != null;
    getLeaves(node.left);
    getLeaves(node.right);
  }

  private void getRightBoundary(TreeNode node) {
    if(node == null || (node.left == null && node.right == null)) return;
    if(node.right != null) {
      getRightBoundary(node.right);
    } else getRightBoundary(node.left);
    ret.add(node.val);
  }

  public static void main(String[] args) {
    BoundaryofBinaryTree boundary = new BoundaryofBinaryTree();
    TreeNode root = new TreeNode(1);
    root.right = new TreeNode(2);
    root.right.left = new TreeNode(3);
    root.right.right = new TreeNode(4);
    System.out.println(boundary.boundaryOfBinaryTree(root));
  }
}
