public class Remove_Duplicates_from_Sorted_Array {
    public static int removeDuplicate(int[] nums) {
        //base case
        int n = nums.length;
        if(n < 2) return n;
        //define the two pointers
        int left = 0, right = 1;
        // remove the dup to the right end
        while(right < n) {
            if(nums[left] != nums[right]){
                left++;
                nums[left] = nums[right];
            }
            right++;
        }
        return left + 1;
    }
    public static void main(String[] args) {
        int[] arr = {0,1,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicate(arr));
    }
}

//nums = [1,2,1]
//          ^   ^
//Output: 2

// [0,1,1,1,1,2,2,3,3,4]
//            ^