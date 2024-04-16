package Basic;

public class MaximumSubarraySumAfterOneOperation {

  /**
   * Given an integer array nums.
   * Perform exactly one operation where you can replace one element nums[i] with nums[i] * nums[i].
   * Return the maximum possible subarray sum after exactly one operation.
   * The subarray must be non-empty.
   * @param nums int[]
   * @return int
   */
  public int maxSumAfterOperation(int[] nums) {
    if(nums == null || nums.length == 0) return 0;
    int sum = 0, sum_operation = 0;
    int ret = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      // dp initialization
      if(i == 0) {
        sum = nums[i];
        sum_operation = nums[i] * nums[i];
      } else{
        // Math.max(prev operation, this round operation)
        sum_operation = Math.max(sum + nums[i] * nums[i], sum_operation +  nums[i]);
        // consider subarray as non-negative, update after sum_operation
        sum = Math.max(0, sum + nums[i]);
      }
      ret = Math.max(ret, sum_operation);
    }
    return ret;
  }

  public static void main(String[] args) {
    MaximumSubarraySumAfterOneOperation maximumSubarraySumAfterOneOperation =
        new MaximumSubarraySumAfterOneOperation();
    int[] test = {2,-1,-4,-3};
    System.out.println(maximumSubarraySumAfterOneOperation.maxSumAfterOperation(test));
  }
}
