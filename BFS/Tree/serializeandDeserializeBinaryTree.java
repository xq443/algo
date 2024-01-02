import java.util.LinkedList;
import java.util.Queue;

public class serializeandDeserializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder ret = new StringBuilder();
        if(root == null) return "";
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if(curr == null) {
                    ret.append("null ");
                    continue;
                }
                ret.append(curr.val).append(" ");
                q.offer(curr.left);
                q.offer(curr.right);
            }
        }
        return ret.toString();
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0) return null;
        String[] str = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(str[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        for (int i = 1; i < str.length; i++) {
            TreeNode curr = q.poll();
            if(!str[i].equals("null")){
                assert curr != null;
                curr.left = new TreeNode(Integer.parseInt(str[i]));
                q.offer(curr.left);
            }
            i++;
            if(!str[i].equals("null")) {
                assert curr != null;
                curr.right = new TreeNode(Integer.parseInt(str[i]));
                q.offer(curr.right);
            }
        }
        return root;
    }
    public static void main(String[] args) {
        serializeandDeserializeBinaryTree s = new serializeandDeserializeBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // Serialize the tree
        String serialized = s.serialize(root);
        System.out.println("Serialized tree: " + serialized);


        // Deserialize the serialized string and obtain the reconstructed tree
        TreeNode deserialized = s.deserialize(serialized);

        // Serialize the reconstructed tree again (for verification)
        String reSerialized = s.serialize(deserialized);
        System.out.println("Re-serialized tree: " + reSerialized);
    }
}
