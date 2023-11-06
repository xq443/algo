package Interval;

public class splitArrayLargestSum {
    /**
     * the largest sum of any subarray is minimized.
     *
     * Return the minimized largest sum of the split.
     *
     * A subarray is a contiguous part of the array.

     * Input: nums = [7,2,5,10,8], k = 2
     * Output: 18
     * Explanation: There are four ways to split nums into two subarrays.
     * The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
     */
    public static int splitArray(int[] nums, int k) {
        int n = nums.length;
        int [] modified = new int[n+1];
        modified[0] = 0;
        for (int i = 1; i <= n ; i++) {
            modified[i] = nums[i-1];
        }
        long[][] dp = new long[n+1][k+1];
        for (int i = 0; i <= n ; i++) {
            for (int j = 0; j <= k ; j++) {
                dp[i][j] = Long.MAX_VALUE;
            }
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n ; i++) {
            for (int kval = 1; kval <= Math.min(k, i); kval++) {
                int sum=0;
                for (int j = i; j >= kval; j--) {
                    sum += modified[j];
                    dp[i][kval] = Math.min(dp[i][kval], Math.max(dp[j-1][kval-1],sum));
                }
            }
        }
        return (int) dp[n][k];
    }
    public static void main(String[] args) {
        int[] nums =  {7,2,5,10,8};
        int k = 2;
        int[] nums_1 = {1,2,3,4,5};
        int k_1 = 2;
        System.out.println(splitArray(nums, k));
        System.out.println(splitArray(nums_1, k_1));
    }

}