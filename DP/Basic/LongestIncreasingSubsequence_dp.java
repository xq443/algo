package Basic;

public class LongestIncreasingSubsequence_dp {
  public int lengthOfLIS(int[] nums){
    int n = nums.length;
    int[] dp = new int[n];
    for (int i = 0; i < n; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
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
    LongestIncreasingSubsequence_dp lis = new LongestIncreasingSubsequence_dp();
    int [] nums = {10,9,2,5,3,7,101,18};
    int [] nums_1 = {0,1,0,3,2,3};
    int [] nums_2 = {7,7,7,7,7,7,7};
    System.out.println(lis.lengthOfLIS(nums));
    System.out.println(lis.lengthOfLIS(nums_1));
    System.out.println(lis.lengthOfLIS(nums_2));
  }
}
