import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {

  /**
   * Given a binary array nums,
   * return the maximum length of a contiguous subarray with an equal number of 0 and 1.
   * @param nums
   * @return
   */
  public int findMaxLength(int[] nums) {
    if(nums == null || nums.length == 0) return 0;
    for (int i = 0; i < nums.length; i++) {
      if(nums[i] == 0) nums[i] = -1;
    }
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);
    int sum = 0, ret = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      if(map.containsKey(sum)) {
        ret = Math.max(ret, i - map.get(sum));
      }else{
        map.put(sum, i);
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    ContiguousArray contiguousArray = new ContiguousArray();
    int[] nums = {0,1};
    System.out.println(contiguousArray.findMaxLength(nums));
  }
}
