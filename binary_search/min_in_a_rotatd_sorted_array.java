public class min_in_a_rotatd_sorted_array {
    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] >= nums[left] && nums[mid] >= nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[left] && nums[mid] < nums[right]) {
                right = mid;
            } else if(nums[mid] > nums[left] && nums[mid] < nums[right]){
                return nums[left];
            } else
                return nums[mid];
        }
        return nums[left];
    }
    public static void main(String[] args) {
        int[] arr = {4,7,1,2};
        System.out.println(findMin(arr));
    }
}
