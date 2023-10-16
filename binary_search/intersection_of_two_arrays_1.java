import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class intersection_of_two_arrays_1 {
    public static int[] intersection(int[] nums1, int[] nums2) {
        //use hashmap and binary search methods
        Arrays.sort(nums2);
        Set<Integer> intersect = new HashSet<>();
        for(int num: nums1) {
            if(find(nums2, num)){
                intersect.add(num);
            }
        }
        //create a new array to store our result
        int[] res = new int[intersect.size()];
        int j = 0;
        for(int num: intersect){
            res[j++] = num;
        }
        return res;
    }
    private static boolean find(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return true;
            else if(nums[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return false;
    }
    public static void main(String[] args) {
        int[] arr1 = {1,2,6,8,2,9};
        int[] arr2 = {2,4,4,4,8,5};
        System.out.println(Arrays.toString(intersection(arr1,arr2)));
    }
}

//time complexity O(NlogN)
//space complexity O(N)
