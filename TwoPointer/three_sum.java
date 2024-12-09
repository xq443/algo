import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class three_sum {
    public static List<List<Integer>> threeSum(int[] nums) {
        //define pointers
        int n = nums.length;
        //sort the array
        Arrays.sort(nums);
        //create new list to store the triplets
        List<List<Integer>> res = new LinkedList<>();
        //iterate
        for(int i = 0; i < n - 2; i++) {
            if( i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = n - 1;

            while(left < right) {
                while (left != i + 1 && nums[left] == nums[left - 1]) {
                    left++;
                }
                left++;
                while(right != n - 1 && nums[right + 1] == nums[right] ) {
                    right--;
                }
                right--;
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0) {
                    List<Integer> sub = new LinkedList<>();
                    sub.add(nums[i]);
                    sub.add(nums[left]);
                    sub.add(nums[right]);
                    res.add(sub);
                    left++;
                    right--;
                } else if (sum < 0) left++;
                else right --;
            }

        }
        return res;
    }
    public static void main(String[] args) {
        int[] test = {0};
        System.out.println(threeSum(test));
    }
}

//Input: nums = [-1,0,1,2,-1,-4]
//Output: [[-1,-1,2],[-1,0,1]