package Tree;

public class symmetricTree {
    public static boolean isSymmetric(TreeNode root){
        if(root == null) return true;
        return callDfs(root.left,root.right);
    }

    private static boolean callDfs(TreeNode leftRoot, TreeNode rightRoot){
        if(leftRoot == null && rightRoot == null) return true;
        if((leftRoot != null && rightRoot == null) || (leftRoot == null && rightRoot != null)) return false;
        if(leftRoot.val != rightRoot.val) return false;

        return callDfs(leftRoot.left, rightRoot.right) && callDfs(leftRoot.right, rightRoot.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        root.left.left = null;
        root.right.left = null;

        System.out.println(isSymmetric(root));
    }
}
