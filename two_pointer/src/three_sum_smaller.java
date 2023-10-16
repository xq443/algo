import java.util.Arrays;

public class three_sum_smaller {
    public static int threeSumSmaller(int[] nums, int target) {
        int n = nums.length;
        //sort the array
        Arrays.sort(nums);
        //define the counter
        int counter = 0;
        //iterate
        for(int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;
            while(left < right) {
                int curSum = nums[i] + nums[right] + nums[left];
                if(curSum < target) {
                    counter += (right - left);
                    left++;
                } else {
                    right--;
                }
            }
        }
        //return the counter
        return counter;
    }
    public static void main(String[] args) {
        int[] test = {-2,0,1,3};
        int val = 2;
        System.out.println(threeSumSmaller(test,val));
    }
}
//O(n2) O(1)