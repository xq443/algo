package Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeandDeserializeBST {
  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    serializeHelper(sb, root);
    return sb.toString();
  }
  private void serializeHelper(StringBuilder sb, TreeNode root) {
    if (root == null)
      return;
    sb.append(root.value).append(" ");
    serializeHelper(sb, root.left);
    serializeHelper(sb, root.right);
  }

  public TreeNode deserialize(String data) {
    if (data == null)
      return null;
    Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(" ")));
    return deserializeHelper(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
  private TreeNode deserializeHelper(Queue<String> queue, int low, int high) {
    if (queue.isEmpty())
      return null;
    TreeNode curr = new TreeNode(Integer.parseInt(queue.poll()));
    if (curr.value < low || curr.value > high)
      return null; // null node
    curr.left = deserializeHelper(queue, low, curr.value);
    curr.right = deserializeHelper(queue, curr.value, high);
    return curr;
  }

  public static void main(String[] args) {
    SerializeandDeserializeBST serializeandDeserializeBST =
        new SerializeandDeserializeBST();
    // Create the BST
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(3);
    root.right = new TreeNode(7);
    root.left.left = new TreeNode(2);
    root.left.right = new TreeNode(4);
    root.right.right = new TreeNode(8);

    String serialized = serializeandDeserializeBST.serialize(root);
    System.out.println("Serialized: " + serialized);

    TreeNode deserialized = serializeandDeserializeBST.deserialize(serialized);
    System.out.println("Deserialized Root Value: " +
                       deserialized.value); // Should be 5
  }
}
