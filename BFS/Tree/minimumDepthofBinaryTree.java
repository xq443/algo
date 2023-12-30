import java.util.LinkedList;
import java.util.Queue;

public class minimumDepthofBinaryTree {
    public int minDepth(TreeNode root) {
        if(root == null) return 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int ret = 1;
        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                assert curr != null;
                if(curr.left == null && curr.right == null) return ret;
                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
            ret++;
        }
        return ret;
    }
    public static void main(String[] args) {
        minimumDepthofBinaryTree m = new minimumDepthofBinaryTree();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(m.minDepth(root));
    }
}
