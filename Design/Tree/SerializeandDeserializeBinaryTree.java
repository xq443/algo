package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeandDeserializeBinaryTree {
  public String serialize(TreeNode root) {
    if (root == null)
      return "";
    StringBuilder ret = new StringBuilder();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      if (node == null)
        ret.append("null,");
      else {
        ret.append(node.value).append(",");
        queue.offer(node.left);
        queue.offer(node.right);
      }
    }
    ret.deleteCharAt(ret.length() - 1);
    return ret.toString();
  }

  public TreeNode deserialize(String data) {
    if (data == null || data.isEmpty())
      return null;
    String[] tokens = data.split(",");
    Queue<TreeNode> queue = new LinkedList<>();
    TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
    queue.offer(root);

    for (int i = 1; i < tokens.length; i++) {
      TreeNode curr = queue.poll();
      if (!tokens[i].equals("null")) {
        curr.left = new TreeNode(Integer.parseInt(tokens[i]));
        queue.offer(curr.left);
      }
      i++;
      if (!tokens[i].equals("null")) {
        curr.right = new TreeNode(Integer.parseInt(tokens[i]));
        queue.offer(curr.right);
      }
    }
    return root;
  }
  public static void main(String[] args) {
    SerializeandDeserializeBinaryTree serializeandDeserializeBinaryTree =
        new SerializeandDeserializeBinaryTree();
    TreeNode root =
        serializeandDeserializeBinaryTree.deserialize("1,2,3,null,4");
    System.out.println(serializeandDeserializeBinaryTree.serialize(root));
  }
}
