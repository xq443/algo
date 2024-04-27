package Interval;

import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSubsequenceOfGivenDifference {
  /**
   * Given an integer array arr and an integer difference
   * return the length of the longest subsequence in arr which is an arithmetic sequence
   * such that the difference between adjacent elements in the subsequence equals difference.
   * @param arr int[]
   * @param difference int
   * @return int
   */
  public int longestSubsequence(int[] arr, int difference) {
    if(arr == null || arr.length == 0) return 0;
    Map<Integer, Integer> map = new HashMap<>();
    map.put(arr[0], 1);
    int ret = 1;

    for (int j : arr) {
      int length = map.getOrDefault(j - difference, 0) + 1;
      map.put(j, length);
      ret = Math.max(ret, length);
    }
    return ret;
  }

  public static void main(String[] args) {
    LongestArithmeticSubsequenceOfGivenDifference longestArithmeticSubsequenceOfGivenDifference
        = new LongestArithmeticSubsequenceOfGivenDifference();
    int[] arr = {1,5,7,8,5,3,4,2,1};
    int difference = -2;
    System.out.println(longestArithmeticSubsequenceOfGivenDifference.longestSubsequence(arr, difference));
  }
}
