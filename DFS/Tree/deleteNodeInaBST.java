package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class deleteNodeInaBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(key > root.val){
            root.right = deleteNode(root.right, key);
        }else if(key < root.val){
            root.left = deleteNode(root.left, key);
        }else{
            if(root.left == null) return root.right;
            else if(root.right == null) return root.left;
            else{
                //extract the minval in the right subtree
                TreeNode min = root.right;
                while(min.left != null){
                    min = min.left;
                }
                root.val = min.val;
                //delete the updated min node
                root.right = deleteNode(root.right, min.val);
            }
        }
        return root;
    }
    //level order print
    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("null ");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current != null) {
                System.out.print(current.val + " ");
                if (current.left != null || current.right != null) {
                    queue.offer(current.left);
                    queue.offer(current.right);
                }
            } else {
                System.out.print("null ");
            }
        }
    }
    public static void main(String[] args) {
        deleteNodeInaBST d = new deleteNodeInaBST();
        //root = [5,3,6,2,4,null,7], key = 3
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        int key = 3;
        printTree(d.deleteNode(root, key));
    }
}
