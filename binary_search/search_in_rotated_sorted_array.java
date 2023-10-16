import java.lang.reflect.Array;
import java.util.Arrays;

public class search_in_rotated_sorted_array {
    public static int search(int[] nums, int target) {

        /**
         * Input: nums = [4,5,6,7,0,1,2], target = 0
         * Output: 4
         */
        //find the smallest value to split two sides.
        //the result should  left = right
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] < nums[right]){
                right = mid;
            }else {
                left = mid + 1;
            }
        }

        //construct two ascending arrays
        int start = left;
        left = 0;
        right = nums.length - 1;
        if(target >= nums[start] && target <= nums[right]) {
            left = start;
        } else {
            right = start;
        }
        //binary search the target
        while(left <= right){
            int mid_point = left + (right - left) / 2;
            if(nums[mid_point] == target) return mid_point;
            else if(nums[mid_point] > target) right = mid_point - 1;
            else left = mid_point + 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,0};
        int target = 4;
        System.out.println(search(arr, target));
    }

}
