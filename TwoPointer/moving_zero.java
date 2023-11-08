import java.util.Arrays;

public class moving_zero {
    public static int[] movingZero(int[] nums){
        //base case
        int n = nums.length;;
        if(n < 2) return nums;
        //define two pointers
        int left = 0, right = 1;
        //move the zeros back
        while(right < n) {
            if(nums[left] != 0) {
                left++;
                right++;
            } else if(nums[right] == 0) {
                right++;
            } else {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
            }
        }
        return nums;
    }
    public static void main(String[] args) {
        int[] arr = {1,3,12,0,0};
        System.out.println(Arrays.toString(movingZero(arr)));
    }
}

//Input: nums = [1,3,12,0,0]

//Output: [1,3,12,0,0]