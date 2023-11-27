import java.util.ArrayList;
import java.util.List;

public class Subsets_v2 {
    public List<List<Integer>> subsets(int[]nums) {
        List<List<Integer>> ret = new ArrayList<>();
        int n = nums.length;
        int total = 1 << n;

        for (int i = 0; i < total; i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if((i & (1 << j)) > 0) subset.add(nums[j]);
            }
            ret.add(subset);
        }
        return ret;
    }
    public static void main(String[] args) {
        Subsets_v2 subsets = new Subsets_v2();

        int[] nums1 = {1, 2, 3};
        List<List<Integer>> result1 = subsets.subsets(nums1);
        System.out.println("Subsets of [1, 2, 3]: " + result1);

        int[] nums2 = {0};
        List<List<Integer>> result2 = subsets.subsets(nums2);
        System.out.println("Subsets of [0]: " + result2);
    }
}
//TC O(N * 2^N)
//SC O(1)
