package Basic;

import java.util.Arrays;

public class MaximumSubarray {
  public Object[] maxSubArray(int[] nums) {
    if(nums == null || nums.length == 0) return new Object[]{new int[0], 0};

    int ret = 0;
    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    int start = 0, end = 0, temp = 0;


    for(int i = 1; i < nums.length; i++) {
      if(dp[i - 1] > 0) {
        dp[i] = nums[i] + dp[i - 1];
      } else {
        dp[i] = nums[i];
        temp = i; // mark starting point
      }

      if(dp[i] > ret) {
        ret = dp[i];
        start = temp;
        end = i;
      }
    }
    int[] arr = Arrays.copyOfRange(nums, start, end + 1);
    return new Object[] {arr, ret};
  }

  public static void main(String[] args) {
    MaximumSubarray maximumSubarray = new MaximumSubarray();
    // Test cases
    Object[] result1 = maximumSubarray.maxSubArray(new int[]{-100});
    System.out.println("Max Sum: " + result1[1] + ", Subarray: " + Arrays.toString((int[]) result1[0])); // Expected: Max Sum: -100, Subarray: [-100]

    Object[] result2 = maximumSubarray.maxSubArray(new int[]{13, -100, 20});
    System.out.println("Max Sum: " + result2[1] + ", Subarray: " + Arrays.toString((int[]) result2[0])); // Expected: Max Sum: 20, Subarray: [20]

    Object[] result3 = maximumSubarray.maxSubArray(new int[]{-100, 20, -10, 60, 80});
    System.out.println("Max Sum: " + result3[1] + ", Subarray: " + Arrays.toString((int[]) result3[0])); // Expected: Max Sum: 150, Subarray: [20, -10, 60, 80]

    Object[] result4 = maximumSubarray.maxSubArray(new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7});
    System.out.println("Max Sum: " + result4[1] + ", Subarray: " + Arrays.toString((int[]) result4[0])); // Expected: Max Sum: 43, Subarray: [18, 20, -7, 12]
  }
}

