package Array;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum{

  // candidates[2,3,6,7], target = 7
  // [[2,2,3], [7]]
  public List<List<Integer>> combinationSum(int target, int[] nums) {
    if((nums == null || nums.length == 0) && target == 0) return null;
    List<List<Integer>> ret = new ArrayList<>();
    combinationSum_dfs(0, target, nums, ret, new ArrayList<>());
    return ret;
  }

  private void combinationSum_dfs(int index, int target, int[] nums, List<List<Integer>> ret, List<Integer> sub) {
    if(target < 0) return;
    if(target == 0) {
      ret.add(new ArrayList<>(sub));
      return;
    }
    for (int i = index; i < nums.length; i++) {
      sub.add(nums[i]);
      combinationSum_dfs(i, target - nums[i], nums, ret, sub);
      sub.remove(sub.size() - 1);
    }
  }

  public static void main(String[] args) {
    CombinationSum combinationSum = new CombinationSum();
    int[] nums = {2,3,6,7};
    int target = 7;
    System.out.println(combinationSum.combinationSum(target, nums));
  }

  // tc O(2^n), sc O(n)
}
