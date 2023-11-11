package backpacking;

public class targetSum {
    /**
     * Input: nums = [1,1,1,1,1], target = 3
     * Output: 5
     * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     * 1 <= nums.length <= 20
     * 0 <= nums[i] <= 1000
     * 0 <= sum(nums[i]) <= 1000
     * -1000 <= target <= 1000
     */
    public static int findTargetSumWays(int[] s, int target){
        int[][] dp =  new int[21][2001];
        int n = s.length;
        int[] modifed = new int[n+1];
        modifed[0] = 0;
        System.arraycopy(s, 0, modifed,1, n);
        int offset = 1000;
        dp[0][0 + offset] = 1;

        for (int i = 1; i <= n; i++) {
            for (int m = -1000 ; m <= 1000 ; m++) {
                if(m-modifed[i] >= -1000 & m-modifed[i] <= 1000){
                    dp[i][m+ offset] += dp[i-1][m-modifed[i]+ offset];
                }
                if(m+modifed[i] >= -1000 & m+modifed[i] <= 1000)
                    dp[i][m+ offset] += dp[i-1][m+modifed[i]+ offset];
            }
        }
        return dp[n][target+offset];
    }
    public static void main(String[] args) {
        int[] s = {1,1,1,1,1};
        int target = 3;
        int[] s_1 = {1};
        int target_1 = 1;
        System.out.println(findTargetSumWays(s, target));
        System.out.println(findTargetSumWays(s_1, target_1));
    }
}
