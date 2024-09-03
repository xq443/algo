package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetII {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> ret = new ArrayList<>();
    if(nums == null || nums.length == 0) return ret;
    Arrays.sort(nums);
    subsetsWithDup_dfs(nums, ret, new ArrayList<>(), 0);
    return ret;
  }
  private void subsetsWithDup_dfs(int[] nums, List<List<Integer>> ret, List<Integer> sub, int index) {
    ret.add(new ArrayList<>(sub));
    for(int i = index; i < nums.length; i++) {
      if(i > index && nums[i] == nums[i - 1]) continue;
      sub.add(nums[i]);
      subsetsWithDup_dfs(nums, ret, sub, i + 1);
      sub.remove(sub.size() - 1);
    }
  }

  public static void main(String[] args) {
    SubsetII subsetII = new SubsetII();
    int[] nums1 = {1, 1, 3};
    List<List<Integer>> result1 = subsetII.subsetsWithDup(nums1);
    System.out.println("Subsets of [1, 1, 3]: " + result1);

    int[] nums2 = {0, 1, 1};
    List<List<Integer>> result2 = subsetII.subsetsWithDup(nums2);
    System.out.println("Subsets of [0, 1]: " + result2);
  }
}
