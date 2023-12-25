import java.util.HashMap;
import java.util.Map;

public class Kth_Largest_Element_in_an_Array_v3 {
    public int findKthLargest_v3(int[] nums, int k){
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
    public Map<Integer, Integer> countOccurrences(int[] nums, int a, int b) {
        Map<Integer, Integer> occurrences = new HashMap<>();

        for (int i = a; i < b; i++) {
            occurrences.put(nums[i], occurrences.getOrDefault(nums[i], 0) + 1);
        }
        return occurrences;
    }
    public static void main(String[] args) {
        Kth_Largest_Element_in_an_Array_v3 kthLargestElementInAnArray3 =  new Kth_Largest_Element_in_an_Array_v3();
        int [] nums = {1,1,2,2,2,2,2,21,0,1,1,1,1};
        int k = 2;
        System.out.println(kthLargestElementInAnArray3.findKthLargest_v3(nums, k));
    }
}

