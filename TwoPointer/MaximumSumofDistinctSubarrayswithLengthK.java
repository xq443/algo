import java.util.HashMap;
import java.util.Map;

public class MaximumSumofDistinctSubarrayswithLengthK {
  public long maximumSubarraySum(int[] nums, int k) {
    if(nums == null || nums.length < k) return 0;
    Map<Integer, Integer> freq = new HashMap<>();
    int curr = 0;
    int max = Integer.MIN_VALUE;
    for(int i = 0; i < nums.length; i++) {
      freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
      curr += nums[i];

      if (i >= k) {
        freq.put(nums[i - k], freq.get(nums[i - k]) - 1);
        if (freq.get(nums[i - k]) == 0)
          freq.remove(nums[i - k]);
        curr -= nums[i - k];
      }
      if (freq.size() == k) {
        max = Math.max(max, curr);
      }
    }
    return max;
  }

  public static void main(String[] args) {
    MaximumSumofDistinctSubarrayswithLengthK m  = new MaximumSumofDistinctSubarrayswithLengthK();
    int[] nums = {1,5,4,2,9,9,9};
    int k = 3;
    System.out.println(m.maximumSubarraySum(nums, k));
  }
}
