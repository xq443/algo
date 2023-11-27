import java.util.Arrays;

public class findpeakelement {
    public static int findPeakElement (int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
    public static void main(String[] args) {
        int[] arr = {1,2,1,3,5,6,4};
        System.out.println(findPeakElement(arr));
    }
}


//[1,2,3,1]
//2

//[1,2,1,3,5,6,4]
//5 or 1