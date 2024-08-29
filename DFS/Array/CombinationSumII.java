package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
  public List<List<Integer>> combinationSumII(int target, int[] nums) {
    if(nums == null || nums.length == 0) return new ArrayList<>();
    Arrays.sort(nums);
    List<List<Integer>> ret = new ArrayList<>();
    combinationSumII_dfs(target, nums, 0, new ArrayList<>(), ret);
    return ret;
  }

  private void combinationSumII_dfs(int target, int[] nums, int index, List<Integer> sub, List<List<Integer>> ret) {
    if(target < 0) return;
    if(target == 0) {
      ret.add(new ArrayList<>(sub));
    }
    for (int i = index; i < nums.length; i++) {
      if(i > index && nums[i] == nums[i - 1]) continue;
      sub.add(nums[i]);
      combinationSumII_dfs(target - nums[i], nums, i + 1, sub, ret);
      sub.remove(sub.size() - 1);
    }
  }

  public static void main(String[] args) {
    CombinationSumII combinationSumII = new CombinationSumII();
    int[] nums = {10,1,2,7,6,1,5};
    int target = 8;
    System.out.println(combinationSumII.combinationSumII(target, nums));
  }
}
