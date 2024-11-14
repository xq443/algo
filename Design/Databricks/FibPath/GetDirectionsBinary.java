package Databricks.FibPath;

class GetDirectionsBinary {

  public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public String getDirections(TreeNode root, int startValue, int destValue) {
    StringBuilder dir1 = new StringBuilder();
    StringBuilder dir2 = new StringBuilder();
    dfs(root, startValue, dir1);
    dfs(root, destValue, dir2);

    // find lowest common ancestor
    int k = 0;
    while (k < dir1.length() && k < dir2.length() && dir1.charAt(k) == dir2.charAt(k)) {
      k++;
    }
    for (int i = k; i < dir1.length(); i++) {
      dir1.setCharAt(i, 'U');
    }
    return dir1.substring(k) + dir2.substring(k);
  }

  public boolean dfs(TreeNode root, int destValue, StringBuilder dir) {
    if (root == null)
      return false;
    if (root.val == destValue)
      return true;

    if (root.left != null) {
      dir.append("L");
      if (dfs(root.left, destValue, dir))
        return true;
      dir.deleteCharAt(dir.length() - 1);
    }
    if (root.right != null) {
      dir.append("R");
      if (dfs(root.right, destValue, dir))
        return true;
      dir.deleteCharAt(dir.length() - 1);
    }
    return false;
  }

  public static void main(String[] args) {
    GetDirectionsBinary gd = new GetDirectionsBinary();

    // Test case 1: Basic test case
    TreeNode root1 = gd.new TreeNode(1, gd.new TreeNode(2, null, null), gd.new TreeNode(3, null, null));
    System.out.println("Test Case 1: " + gd.getDirections(root1, 2, 3)); // Expected output: "UR"

    // Test case 2: Start and destination are the same
    TreeNode root2 = gd.new TreeNode(1, gd.new TreeNode(2, null, null), gd.new TreeNode(3, null, null));
    System.out.println("Test Case 2: " + gd.getDirections(root2, 2, 2)); // Expected output: ""

    // Test case 3: Start or destination out of bounds
    TreeNode root3 = gd.new TreeNode(1, gd.new TreeNode(2, null, null), gd.new TreeNode(3, null, null));
    try {
      System.out.println("Test Case 3: " + gd.getDirections(root3, 2, 4)); // Expected output: Error handling
    } catch (AssertionError e) {
      System.out.println("Test Case 3: AssertionError: " + e.getMessage());
    }

    // Test case 4: Larger tree
    TreeNode root4 = gd.new TreeNode(1,
        gd.new TreeNode(2, gd.new TreeNode(4, null, null), gd.new TreeNode(5, null, null)),
        gd.new TreeNode(3, gd.new TreeNode(6, null, null), gd.new TreeNode(7, null, null))
    );
    System.out.println("Test Case 4: " + gd.getDirections(root4, 4, 7)); // Expected output: "UUURR"

    // Test case 5: Minimal path change
    TreeNode root5 = gd.new TreeNode(1, gd.new TreeNode(2, null, null), gd.new TreeNode(3, null, null));
    System.out.println("Test Case 5: " + gd.getDirections(root5, 2, 3)); // Expected output: "UUR"

    // Test case 6: Complex tree
    TreeNode root6 = gd.new TreeNode(1,
        gd.new TreeNode(2, gd.new TreeNode(4, gd.new TreeNode(8, null, null), null), gd.new TreeNode(5, null, null)),
        gd.new TreeNode(3, gd.new TreeNode(6, null, null), gd.new TreeNode(7, null, null))
    );
    System.out.println("Test Case 6: " + gd.getDirections(root6, 8, 7)); // Expected output: "UUUURR"
  }
}
