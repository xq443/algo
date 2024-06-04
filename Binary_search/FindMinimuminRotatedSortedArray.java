public class FindMinimuminRotatedSortedArray {

  /**
   * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
   * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
   *
   * @param nums int[]
   * @return int
   */
  public int findMin(int[] nums) {
    if(nums == null || nums.length == 0) return -1;
    int left = 0, right = nums.length - 1;
    int target = nums[right];

    while(left <= right) {
      int mid = left + (right - left) / 2;
      if(nums[mid] > target) {
        left = mid + 1;
      } else if(nums[mid] < target) {
        right = mid - 1;
      } else return nums[mid];
    }
    return nums[left];
  }

  public static void main(String[] args) {
    FindMinimuminRotatedSortedArray findMinimuminRotatedSortedArray =
        new FindMinimuminRotatedSortedArray();
    int[] nums = {4,5,6,7,-1,0,1,2};
    System.out.println(findMinimuminRotatedSortedArray.findMin(nums));
  }
}
