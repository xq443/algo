package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PermutationsII {
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> ret = new ArrayList<>();
    if(nums == null || nums.length == 0) return ret;
    HashSet<Integer> visited = new HashSet<>();
    Arrays.sort(nums);
    permuteUnique_dfs(ret, nums, new ArrayList<>(), visited);
    return ret;
  }

  private void permuteUnique_dfs(List<List<Integer>> ret, int[] nums, List<Integer> sub, HashSet<Integer> visited) {
    if(sub.size() == nums.length) {
      ret.add(new ArrayList<>(sub));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if(visited.contains(i)) continue;
      if(i > 0 && nums[i] == nums[i - 1] && !visited.contains(i - 1)) continue;
      sub.add(nums[i]);
      visited.add(i);
      permuteUnique_dfs(ret, nums, sub, visited);
      sub.remove(sub.size() - 1);
      visited.remove(i);
    }
  }

  public static void main(String[] args) {
    PermutationsII permuntationsII = new PermutationsII();
    int[] nums = {1,1,2};
    System.out.println(permuntationsII.permuteUnique(nums));
  }
}
