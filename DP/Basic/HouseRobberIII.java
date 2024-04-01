package Basic;

public class HouseRobberIII {
  public static class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
      this.val = val;
    }
  }
  public int rob(TreeNode root) {
    if(root == null) return 0;
    // 0 -> no , 1 -> yes
    int[] ret = findValue(root);
    return Math.max(ret[0], ret[1]);
  }
  public int[] findValue(TreeNode root) {
    if(root == null) return new int[]{0,0};
    int[] left = findValue(root.left);
    int[] right = findValue(root.right);
    int[] ret = new int[2];
    ret[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    ret[1] = root.val + left[0] + right[0];
    return ret;
  }

  public static void main(String[] args) {
    HouseRobberIII houseRobberIII = new HouseRobberIII();
    // Example 1: [3,2,3,null,3,null,1]
    TreeNode root1 = new TreeNode(3);
    root1.left = new TreeNode(2);
    root1.right = new TreeNode(3);
    root1.right.right = new TreeNode(1);
    root1.right.left = new TreeNode(3);
    int result1 = houseRobberIII.rob(root1);
    System.out.println("Example 1: " + result1); // Expected output: 7

    // Example 2: [3,4,5,1,3,null,1]
    TreeNode root2 = new TreeNode(3);
    root2.left = new TreeNode(4);
    root2.right = new TreeNode(5);
    root2.left.left = new TreeNode(1);
    root2.left.right = new TreeNode(3);
    root2.right.right = new TreeNode(1);
    int result2 = houseRobberIII.rob(root2);
    System.out.println("Example 2: " + result2); // Expected output: 9
  }
}
