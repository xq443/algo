package Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

  /**
   * Given two integer arrays preorder and inorder
   * construct and return the binary tree.
   * @param preorder int[]
   * @param inorder int[]
   * @return TreeNode
   */

  // preorder: root -> left -> right
  // inorder: left -> root -> right
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if(preorder == null || preorder.length == 0 ||
    inorder == null || inorder.length == 0) return null;

    Map<Integer, Integer> nodeToIdx = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      nodeToIdx.put(inorder[i], i);
    }
    return constructBST(preorder, inorder, nodeToIdx, 0, inorder.length - 1, 0);
  }
  private TreeNode constructBST(int[] preorder, int[] inorder, Map<Integer, Integer> nodeToIdx,
      int start, int end, int first) {
    if(start > end) return null;
    TreeNode root = new TreeNode(preorder[first]);
    int rootIdx = nodeToIdx.get(preorder[first]);

    // traverse based on the preorder array
    // first in left and right represent the root node in subtrees
    root.left = constructBST(preorder, inorder, nodeToIdx,
        start, rootIdx - 1, first + 1);
    root.right = constructBST(preorder, inorder, nodeToIdx,
        rootIdx + 1, end, first + (rootIdx - start + 1));
    return root;
  }
  public static void main(String[] args) {
    ConstructBinaryTreeFromPreorderAndInorderTraversal c =
        new ConstructBinaryTreeFromPreorderAndInorderTraversal();
    int[] inorder = {9,3,15,20,7};
    int[] preorder = {3,9,20,15,7};
    printLevelOrder(c.buildTree(preorder, inorder));
  }

  public static void printLevelOrder(TreeNode root) {
    if (root == null) {
      System.out.println("Tree is empty.");
      return;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      for (int i = 0; i < levelSize; i++) {
        TreeNode curr = queue.poll();
        assert curr != null;
        System.out.print(curr.val + " ");
        if (curr.left != null) {
          queue.offer(curr.left);
        }
        if (curr.right != null) {
          queue.offer(curr.right);
        }
      }
      System.out.println();
    }
  }
}
