import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 */

class two_sum {
    public static int[] twoSum(int[] num, int target) {
        //use the hashmap to store and search
        Map<Integer, Integer> numMap = new HashMap<>();
        for(int i = 0; i < num.length; i++) {
            numMap.put(num[i], i);
        }

        for(int i = 0; i < num.length; i++) {
            int complement = target - num[i];
            if(numMap.containsKey(complement) && numMap.get(complement) != i) {
                return new int[] {i, numMap.get(complement)};
            }
        } throw new IllegalArgumentException("No pair is found.");
    }
    public static void main(String[] args) {
        int[] nums = {2, 5, 11, 28};
        int target = 7;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }
}