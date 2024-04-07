package quickSort;

public class Kth_Largest_Element_in_an_Array_v2 {
    //S S S S O O O L L L
    public int findKthLargest_v2(int[] nums, int k){
        return quickSelect(nums, 0, nums.length - 1, k);
    }
    private int quickSelect(int[] nums, int a, int b, int k){
        int i = a, j = b;
        int t = a;
        int pivot = nums[(a+b)/2];
        while(t <= j){
            if(nums[t] < pivot){
                swap_findKthLargest(nums, t, i);
                t++;
                i++;
            }else if(nums[t] == pivot){
                t++;
            }else{
                swap_findKthLargest(nums, t, j);
                j--;
            }
        }
        if(b - j >= k){
            return quickSelect(nums, j+1, b, k);
        }else if(b-i+1 >= k){
            return pivot;
        }else{
            return quickSelect(nums, a, i - 1, k - (b-i+1));
        }
    }
    private void swap_findKthLargest(int [] nums, int i, int j){
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
    public static void main(String[] args) {
        Kth_Largest_Element_in_an_Array_v2 kthLargestElementInAnArray2 =  new Kth_Largest_Element_in_an_Array_v2();
        int [] nums = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(kthLargestElementInAnArray2.findKthLargest_v2(nums, k));
    }
}
//TC O(N) O(N^2)
//SC O(1)
