public class kth_Largest_Element_in_an_Array {
    public  int findKthLargest(int [] nums, int k){
        int left = Integer.MIN_VALUE / 2, right = Integer.MAX_VALUE / 2;
        while(left < right){
            int mid = right - (right - left) / 2;
            // in the nums array, there are more than k elements with the value that are greater than(or equal to) the value of mid
            if(count(nums, mid) >= k) left = mid;
            else right = mid - 1;
        }
        return left;
    }
    private int count(int [] nums, int m){
        int ret = 0;
        for(int num :  nums){
            ret += (num >= m) ? 1 :0;
        }
        return ret;
    }
    public static void main(String[] args) {
        kth_Largest_Element_in_an_Array kthLargestElementInAnArray =  new kth_Largest_Element_in_an_Array();
        int [] nums = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(kthLargestElementInAnArray.findKthLargest(nums, 2));
    }
}
//TC O(NlogC)
//SC O(1)
