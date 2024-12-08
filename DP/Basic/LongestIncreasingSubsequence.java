package Basic;

public class LongestIncreasingSubsequence {
    /**
     * This is the dynamic programming of the second type.
     * We consider what the second-largest element of the Longest Increasing Subsequence (LIS) ending with nums[i] is.
     * So, we search for the best index, j, among all indices before i:
     * if nums[j] < nums[i], then the LIS ending with j can be included in the LIS ending with i.
     * Obviously, the best j is the one with the largest dp[j].
     *
     * The time complexity of this dynamic programming is O(N^2).
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if(nums[j] <  nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }
    public static void main(String[] args) {
        int [] nums = {10,9,2,5,3,7,101,18};
        int [] nums_1 = {0,1,0,3,2,3};
        int [] nums_2 = {7,7,7,7,7,7,7};
        System.out.println(lengthOfLIS(nums));
        System.out.println(lengthOfLIS(nums_1));
        System.out.println(lengthOfLIS(nums_2));
    }
}
