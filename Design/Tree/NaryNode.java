package Tree;

import java.util.List;

public class NaryNode {
  int value;
  List<NaryNode> children;

  public NaryNode(int value) { this.value = value; }

  public NaryNode(int value, List<NaryNode> children) {
    this.value = value;
    this.children = children;
  }

  public NaryNode() {}
}
