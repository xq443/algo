import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class findLargestValueinEachTreeRow_v2 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            int maxVal = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                assert curr != null;
                maxVal = Math.max(maxVal, curr.val);
                if(curr.left != null) queue.offer(curr.left);
                if(curr.right != null) queue.offer(curr.right);
            }
            ret.add(maxVal);
        }
        return ret;
    }
    public static void main(String[] args) {
        findLargestValueinEachTreeRow_v2 f = new findLargestValueinEachTreeRow_v2();
        // Creating the tree based on the input [1,3,2,5,3,null,9]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);

        // Testing the largestValues method with the given input
        List<Integer> result = f.largestValues(root);
        // Printing the output
        System.out.println("Output: " + result); // Expected output: [1, 3, 9]
    }
}
