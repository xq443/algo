package BinarySearchTree;

public class UniqueTrees {
  public int numTrees(int n) {
    // [1, k]
    int[] dp = new int[n + 1];

    // Base cases:
    // 0 nodes (empty tree) and 1 node (the node itself)
    dp[0] = 1;
    dp[1] = 1;

    // Fill the dp array for each count of nodes from 2 to n.
    for (int i = 2; i <= n; i++) { // >= 2 nodes
      dp[i] = 0; // reset

      // The number of unique BSTs with i nodes is the sum of the product of:
      // - The number of BSTs formed by the left subtree (dp[j]).
      // - The number of BSTs formed by the right subtree (dp[i-1-j]).
      for (int j = 0; j < i; j++) {
        dp[i] += dp[j] * dp[i - 1 - j];
      }
    }
    return dp[n];
  }

  public static void main(String[] args) {
    UniqueTrees uniqueTrees = new UniqueTrees();
    System.out.println(uniqueTrees.numTrees(3));
    System.out.println(uniqueTrees.numTrees(4));
    System.out.println(uniqueTrees.numTrees(1));
  }
}
