import java.util.LinkedList;
import java.util.Queue;

public class checkCompletenessofaBinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        if(root == null) return false;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(q.peek() != null){
            TreeNode curr = q.poll();
            q.offer(curr.left);
            q.offer(curr.right);
        }
        while(!q.isEmpty() && q.peek() == null){
            q.poll();
        }
        return q.isEmpty();
    }

    public static void main(String[] args) {
        checkCompletenessofaBinaryTree c = new checkCompletenessofaBinaryTree();
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
