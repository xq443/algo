
import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class balanceABinarySearchTree {
    public TreeNode balanceBST(TreeNode root) {
        if(root == null) return null;
        if(root.left == null && root.right == null) return  root;

        //in-order
        List<Integer> ret = new ArrayList<>();
        inOrder(ret, root);
        //add to the tree
        root = createBST(ret, 0, ret.size() - 1);
        return root;
    }
    private void inOrder(List<Integer> ret, TreeNode root){
        if(root == null) return;
        inOrder(ret, root.left);
        ret.add(root.val);
        inOrder(ret, root.right);
    }
    private TreeNode createBST(List<Integer> ret, int start, int end){
        if (start > end) return null;

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(ret.get(mid));
        root.left = createBST(ret, start, mid - 1);
        root.right = createBST(ret, mid + 1, end);
        return root;
    }
    public static void main(String[] args) {
        // Test Example 1: [1,null,2,null,3,null,4,null,null]
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.right = new TreeNode(3);
        root1.right.right.right = new TreeNode(4);

        balanceABinarySearchTree solution = new balanceABinarySearchTree();
        TreeNode balancedTree1 = solution.balanceBST(root1);
        printInorder(balancedTree1);
        System.out.println('\n');

        // Test Example 2: [2,1,3]
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);

        TreeNode balancedTree2 = solution.balanceBST(root2);
        printInorder(balancedTree2);
    }
    private static void printInorder(TreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(root.val + " ");
            printInorder(root.right);
        }
    }
}
