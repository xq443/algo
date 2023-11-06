package Interval;

public class largestSumofAverages {
    /**
     * Input: nums = [9,1,2,3,9], k = 3
     * Output: 20.00000
     * Explanation:
     * The best choice is to partition nums into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
     * We could have also partitioned nums into [9, 1], [2], [3, 9], for example.
     * That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
     */
    public static double largestSumOfAverages(int[] nums, int k){
        int n = nums.length;
        double[] modified = new double[n+1];
        double[][] dp = new double[n+1][k+1];
        modified[0] = 0;
        for (int i = 1; i <= n; i++) {
            modified[i] = nums[i-1];
        }
        for (int i = 0; i <= n ; i++) {
            for (int j = 0; j <= k ; j++) {
                dp[i][j] = Double.NEGATIVE_INFINITY;
            }
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int kVal = 1; kVal <= Math.min(i,k) ; kVal++) {
                int sum = 0;
                    for (int j = i; j >= kVal ; j--) {
                        sum += modified[j];
                        dp[i][kVal] = Math.max(dp[i][kVal], dp[j - 1][kVal - 1] + (double) sum /(i - j + 1));
                    }
                }
            }
        return dp[n][k];
    }
    public static void main(String[] args) {
        int[] nums =  {9,1,2,3,9};
        int k = 3;
        int[] nums_1 = {1,2,3,4,5,6,7};
        int k_1 = 4;
        System.out.println(largestSumOfAverages(nums, k));
        System.out.println(largestSumOfAverages(nums_1, k_1));
    }
}
