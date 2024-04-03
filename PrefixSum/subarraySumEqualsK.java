package Prefix_sum;

import java.util.HashMap;

public class subarraySumEqualsK {
    /**
     * Input: nums = [1,1,1], k = 2
     * Output: 2
     *
     * Input: nums = [1,2,3], k = 3
     * Output: 2
     */
    public int subarraySum(int[] nums, int k){
        int ret = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                ret += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ret;
    }
    public static void main(String[] args) {
        subarraySumEqualsK subarraySumEqualsK = new subarraySumEqualsK();
        int[] nums = {1,1,1};
        int k = 1;
        System.out.println(subarraySumEqualsK.subarraySum(nums, k));
    }
}
