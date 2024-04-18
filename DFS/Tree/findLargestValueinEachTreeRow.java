package Tree;

import java.util.ArrayList;
import java.util.List;
public class findLargestValueinEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if(root == null) return ret;
        dfs_findLargestValueinEachTreeRow(root, ret,0);

        return ret;
    }
    private void dfs_findLargestValueinEachTreeRow(TreeNode root, List<Integer> ret, int currDepth){
        if(root == null) return;
        if(currDepth >= ret.size()){
            ret.add(root.val);
        }else{//currDepth < ret.size()
            int maxVal = Math.max(root.val, ret.get(currDepth));
            ret.set(currDepth, maxVal);
        }
        dfs_findLargestValueinEachTreeRow(root.left, ret, currDepth + 1);
        dfs_findLargestValueinEachTreeRow(root.right, ret, currDepth + 1);
    }

    public static void main(String[] args) {
        findLargestValueinEachTreeRow f = new findLargestValueinEachTreeRow();
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
