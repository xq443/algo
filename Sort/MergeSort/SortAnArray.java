import java.util.Arrays;

public class SortAnArray {

  /**
   * Given an array of integers nums, sort the array in ascending order and return it.
   *
   * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity
   * and with the smallest space complexity possible.
   * @param nums int[]
   * @return int[]
   */
  public int[] sortArray(int[] nums) {
    if(nums == null || nums.length == 0) return nums;
    mergesort(nums, 0, nums.length - 1);
    return nums;
  }

  private void mergesort(int[] nums, int left, int right) {
    if(left < right) {
      int mid = left + (right - left) / 2;
      mergesort(nums, left, mid);
      mergesort(nums, mid + 1, right);

      merge(nums, left, mid, right);
    }
  }
  private void merge(int[] nums, int left, int mid, int right) {
    int[] temp = new int[right - left + 1];
    int i = left, j = mid + 1, idx = 0;
    while(i <= mid && j <= right) {
      if(nums[i] <= nums[j]) {
        temp[idx++] = nums[i++];
      }else{
        temp[idx++] = nums[j++];
      }
    }
    while(i <= mid) {
      temp[idx++] = nums[i++];
    }
    while(j <= right) {
      temp[idx++] = nums[j++];
    }
    for(i = left, idx = 0; i <= right; i++, idx++) {
      nums[i] = temp[idx];
    }
  }

  public static void main(String[] args) {
    SortAnArray sortAnArray = new SortAnArray();
    int[] nums = {5,2,3,1};
    System.out.println(Arrays.toString(sortAnArray.sortArray(nums)));
  }
}
// O(NlogN) O(N)
