package Array;

import java.util.ArrayList;
import java.util.List;
public class Subsets {
    public List<List<Integer>> subsets(int[]nums){
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        subset_dfs(ret, subset, 0, nums);
        return ret;
    }
    private void subset_dfs(List<List<Integer>> ret, List<Integer> subset, int index, int[] nums){
        ret.add(new ArrayList<>(subset));
        // It creates a shallow copy of the subset list.
        // This step is crucial to avoid adding a reference to the same subset list multiple times.
        for (int i = index; i < nums.length; i++) {
            subset.add(nums[i]);
            subset_dfs(ret, subset, i + 1, nums);
            subset.remove(subset.size() - 1);
        }
    }
    public static void main(String[] args) {
        Subsets subsets = new Subsets();

        int[] nums1 = {1, 2, 3};
        List<List<Integer>> result1 = subsets.subsets(nums1);
        System.out.println("Subsets of [1, 2, 3]: " + result1);

        int[] nums2 = {0};
        List<List<Integer>> result2 = subsets.subsets(nums2);
        System.out.println("Subsets of [0]: " + result2);
    }
}
//TC O(N * 2^N)
//SC O(N)
