package Basic;

import java.util.Arrays;

public class MaximumSubarray_v2 {
  public Object[] maxSubArray(int[] nums) {
    if(nums == null || nums.length == 0) return new Object[]{new int[0], 0};
    int max = nums[0];
    int currSum = nums[0];
    int start = 0, end = 0, temp = 0;

    for(int i = 1; i < nums.length; i++) {
      if(currSum > 0) {
        currSum += nums[i];
      } else {
        currSum = nums[i];
        temp = i;
      }
      if(currSum > max) {
        max = currSum;
        start = temp;
        end = i;
      }
    }
    int[] ret = Arrays.copyOfRange(nums, start, end + 1);
    return new Object[] {ret, max};
  }

  public static void main(String[] args) {
    MaximumSubarray_v2 maximumSubarray = new MaximumSubarray_v2();
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
