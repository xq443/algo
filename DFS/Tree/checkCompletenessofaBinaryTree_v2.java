package Tree;

public class checkCompletenessofaBinaryTree_v2 {
    public boolean isCompleteTree(TreeNode root) {
        if(root == null) return false;
        int cnt = countNode(root);
        return dfs_isCompleteTree(root, 0, cnt);
    }
    private boolean dfs_isCompleteTree(TreeNode root, int index, int count){
        if(root == null) return true;
        if(index >= count) return false;
        return dfs_isCompleteTree(root.left, 2 * index + 1, count) &&
                dfs_isCompleteTree(root.right, 2 * index + 2, count);
    }
    private int countNode(TreeNode root){
        if(root == null) return 0;
        return 1 + countNode(root.left) + countNode(root.right);
    }

    public static void main(String[] args) {
        checkCompletenessofaBinaryTree_v2 c = new checkCompletenessofaBinaryTree_v2();
        // Create the binary tree: [1,2,3,4,5,6]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println(c.isCompleteTree(root));
    }
}
