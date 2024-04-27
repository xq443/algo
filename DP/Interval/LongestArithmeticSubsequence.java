package Interval;

import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSubsequence {

  /**
   * Given an array nums of integers,
   * return the length of the longest arithmetic subsequence in nums.
   *
   * Note that:
   *
   * A subsequence is an array that can be derived from another array
   * by deleting some or no elements without changing the order of the remaining elements.
   * A sequence seq is arithmetic if seq[i + 1] - seq[i] are all the same value
   * (for 0 <= i < seq.length - 1).
   *
   * @param nums
   * @return
   */
  public int longestArithSeqLength(int[] nums) {
    if(nums == null || nums.length == 0) return 0;
    int n = nums.length;

    // dp[i] represents the length of the longest arithmetic subsequence
    // ending at nums[i] with difference j
    Map<Integer, Integer>[] dp = new HashMap[n];
    int ret = 2;
    for (int i = 0; i < n; i++) {
      dp[i] = new HashMap<>();
      for (int j = 0; j < i; j++) {
        int diff = nums[i] - nums[j];
        int length = dp[j].getOrDefault(diff, 1) + 1;
        dp[i].put(diff, length);
        ret = Math.max(ret, length);
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    LongestArithmeticSubsequence longestArithmeticSubsequence = new LongestArithmeticSubsequence();
    int[] nums1 = {9,4,7,2,10};
    int[] nums2 = {20,1,15,3,10,5,8};
    System.out.println(longestArithmeticSubsequence.longestArithSeqLength(nums1));
    System.out.println(longestArithmeticSubsequence.longestArithSeqLength(nums2));
  }
}
