package Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeandDeserializeNaryTree {
  public String serialize(NaryNode root) {
    StringBuilder sb = new StringBuilder();
    serializeHelper(sb, root);
    return sb.toString();
  }
  private void serializeHelper(StringBuilder sb, NaryNode root) {
    if (root == null)
      return;
    sb.append(root.value).append(" ");

    if (root.children != null) {
      for (NaryNode child : root.children) {
        serializeHelper(sb, child);
      }
    }

    // Mark the end of children for this node
    sb.append("# ");
  }

  public NaryNode deserialize(String data) {
    if (data == null || data.isEmpty())
      return null;
    String[] tokens = data.split(" ");
    Queue<String> queue = new LinkedList<>(Arrays.asList(tokens));
    return deserializeHelper(queue);
  }

  private NaryNode deserializeHelper(Queue<String> queue) {
    if (queue.isEmpty())
      return null;

    String curr = queue.poll();
    if (curr.equals("#"))
      return null;

    NaryNode newNode = new NaryNode(Integer.parseInt(curr), new ArrayList<>());

    while (!queue.isEmpty()) {
      NaryNode child = deserializeHelper(queue);
      if (child == null)
        break;
      newNode.children.add(child);
    }
    return newNode;
  }

  public static void main(String[] args) {
    SerializeandDeserializeNaryTree serializeandDeserializeNaryTree =
        new SerializeandDeserializeNaryTree();
    NaryNode root = new NaryNode(
        1, Arrays.asList(
               new NaryNode(3, Arrays.asList(new NaryNode(5), new NaryNode(6))),
               new NaryNode(2), new NaryNode(4)));

    String serialized = serializeandDeserializeNaryTree.serialize(root);
    System.out.println("Serialized: " + serialized);

    NaryNode deserialized =
        serializeandDeserializeNaryTree.deserialize(serialized);
    System.out.println("Deserialized Root Value: " +
                       deserialized.value); // Should be 1
  }
}
