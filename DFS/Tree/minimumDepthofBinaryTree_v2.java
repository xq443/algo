package Tree;

public class minimumDepthofBinaryTree_v2 {
    public int minDepth(TreeNode root) {
        //base case
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;

        if(root.left != null && root.right == null) return 1 + minDepth(root.right);
        if(root.right != null && root.left == null) return 1 + minDepth(root.left);
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
    public static void main(String[] args) {
        minimumDepthofBinaryTree_v2 m = new minimumDepthofBinaryTree_v2();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(m.minDepth(root));
    }
}
