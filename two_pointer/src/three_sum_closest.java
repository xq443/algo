import java.util.Arrays;

public class three_sum_closest {
    public static int threeSumCloset(int[] nums, int target) {
        int n = nums.length;
        //sort the array
        Arrays.sort(nums);
        //define gap, min
        int gap = Integer.MAX_VALUE, min = 0;
        //iterate and define two pointers
        for(int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;
            while(left < right) {
                int curSum = nums[i] + nums[right] + nums[left];
                if(curSum == target) return target;
                else if(curSum < target) left++;
                else right--;
                int curGap = Math.abs(curSum - target);
                if(curGap < gap) {
                    gap = curGap;
                    min = curSum;
                }
            }
        }
        //return the res
        return min;
    }

    public static void main(String[] args) {
        int[] test = {-1, 2, 1, -4};
        int val = 1;
        System.out.println(threeSumCloset(test,val));
    }
}
