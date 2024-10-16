/**
 * Given a sorted array, two integers a and b
 * find the count of numbers in the range[a, b)
 */

public class RangeCount {
  public int countInRange(int[] nums, int a, int b) {

    int leftBound = binarySearch(nums, a);
    int rightBound = binarySearch(nums, b);
    return  rightBound - leftBound;

  }

  // (>= a) - (>= b)
  private int binarySearch(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while(left <= right) {
      int mid = left + (right - left) / 2;
      if(nums[mid] >= target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  public static void main(String[] args) {
    RangeCount rangeCount = new RangeCount();
    int[] sortedArray = {1, 3, 5, 7, 9, 11, 13};
    int a = 4;
    int b = 12;
    int count = rangeCount.countInRange(sortedArray, a, b);
    System.out.println("Count of elements in range [" + a + ", " + b + ") is: " + count);
  }
}
// time complexity: O(logN)
// space complexity: O(1)
