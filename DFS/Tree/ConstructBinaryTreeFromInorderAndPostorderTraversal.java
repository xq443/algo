package Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

  /**
   * Given two integer arrays inorder and postorder
   * construct and return the binary tree
   * @param inorder int[]
   * @param postorder int[]
   * @return int[]
   */

  // inorder: left -> root -> right
  // postorder: left -> right -> root
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    if(inorder == null || inorder.length == 0 ||
        postorder == null || postorder.length == 0) return null;
    // the last element in postorder  -> root, record the value
    // slice the inorder array according to the root index
    // which means we need to build a hashmap  key: treenode.value & value: idx in inorder
    Map<Integer, Integer> nodeToIdx = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      nodeToIdx.put(inorder[i], i);
    }

    return constructBST(inorder, postorder, nodeToIdx, 0, inorder.length - 1,
        postorder.length -1);
  }
  private TreeNode constructBST (int[] inorder, int[] postorder, Map<Integer, Integer> nodeToIdx,
      int start, int end, int last) {
    if(start > end) return null;

    TreeNode root = new TreeNode(postorder[last]);
    int rootIdx = nodeToIdx.get(postorder[last]); // rootIdx in the inorder array
    // traverse based on postorder array for the known rootIdx right most position
    root.left = constructBST(inorder, postorder, nodeToIdx,
        start, rootIdx - 1, last - (end - rootIdx + 1));
    root.right = constructBST(inorder, postorder, nodeToIdx,
        rootIdx + 1,  end, last - 1);
    return root;
  }

  public static void main(String[] args) {
    ConstructBinaryTreeFromInorderAndPostorderTraversal c =
        new ConstructBinaryTreeFromInorderAndPostorderTraversal();
    int[] inorder = {9,3,15,20,7};
    int[] postorder = {9,15,7,20,3};
    printLevelOrder(c.buildTree(inorder, postorder));
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
