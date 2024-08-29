package Array;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ret = new ArrayList<>();
    if(nums == null || nums.length == 0) return ret;
    permute_dfs(ret, nums, new ArrayList<>());
    return ret;
  }

  private void permute_dfs(List<List<Integer>> ret, int[] nums, List<Integer> sub) {
    if(sub.size() == nums.length) {
      ret.add(new ArrayList<>(sub));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if(sub.contains(nums[i])) continue;
      sub.add(nums[i]);
      permute_dfs(ret, nums, sub);
      sub.remove(sub.size() - 1);
    }
  }

  public static void main(String[] args) {
    Permutations permutation = new Permutations();
    int[] nums = {1,2};
    System.out.println(permutation.permute(nums));
  }
}
