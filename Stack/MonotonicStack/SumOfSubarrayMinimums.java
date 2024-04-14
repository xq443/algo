package MonotonicStack;

import java.util.Stack;

public class SumOfSubarrayMinimums {

  /**
   * Given an array of integers arr, find the sum of min(b),
   * where b ranges over every (contiguous) subarray of arr.
   * Since the answer may be large, return the answer modulo 10^9 + 7.
   * @param nums
   * @return integer
   */
  public int sumSubarrayMins(int[] nums) {
    if(nums == null || nums.length == 0) return 0;
    final int MOD = (int) (1e9 + 7);
    int ret = 0;
    Stack<Integer> stack = new Stack<>();
    stack.push(-1);

    for (int i = 0; i <= nums.length; i++) {
      int x = (i < nums.length) ? nums[i] : -1;
      while(stack.size() > 1 && nums[stack.peek()] >= x) {
        int j = stack.pop();
        ret += nums[j] * (j - stack.peek()) * (i - j);
      }
      stack.push(i);
    }
    return ret % MOD;
  }

  public static void main(String[] args) {
    SumOfSubarrayMinimums sumOfSubarrayMinimums = new SumOfSubarrayMinimums();
    int[] arr = {3,1,2,4};
    System.out.println(sumOfSubarrayMinimums.sumSubarrayMins(arr));
  }
}
