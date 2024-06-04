public class BinarySearch {
  /**
   * Given an array of integers nums which is sorted in ascending order,
   * and an integer target, write a function to search target in nums.
   * If target exists, then return its index. Otherwise, return -1.
   * @param nums int[]
   * @param target int
   * @return int
   */
  public int search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while(left <= right) {
      int mid = left + (right - left) / 2;
      if(nums[mid] == target) return mid;
      else if(nums[mid] < target) left = mid + 1;
      else right = mid - 1;
    }
    return -1;
  }

  public static void main(String[] args) {
    BinarySearch binarySearch = new BinarySearch();
    int[] nums = {-1,0,3,5,9,12};
    int target = -1;
    int another = 15;
    int ret1 = binarySearch.search(nums, target);
    int ret2 = binarySearch.search(nums, another);
    System.out.println(ret1);
    System.out.println(ret2);
  }
}
