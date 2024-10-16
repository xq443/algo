import java.util.Arrays;

public class LargeArrayRangeCount {
  public int largeCountInRange(int[] largeArray, int a, int b, int chuckSize) {
    int total = 0;
    for(int i = 0; i < largeArray.length; i += chuckSize) {
      int end = Math.min(i + chuckSize, largeArray.length);
      int[] chuck = Arrays.copyOfRange(largeArray, i, end);
      total += countInRangeChuck(chuck, a, b);
    }
    return total;
  }

  // (>= a) - (>= b)
  private int countInRangeChuck(int[] chucks, int a, int b) {
    int leftBound = binarySearch(chucks, a);
    int rightBound = binarySearch(chucks, b);
    return rightBound - leftBound;
  }
  private int binarySearch(int[] chucks, int target) {
    int left = 0, right = chucks.length - 1;
    while(left <= right) {
      int mid = left + (right - left) / 2;
      if(chucks[mid] >= target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return right;
  }

  public static void main(String[] args) {
    LargeArrayRangeCount largeArrayRangeCount = new LargeArrayRangeCount();
    // Simulating a large array

    int[] sortedArray = {1, 3, 5, 7, 9, 11, 13};
    int a = 4;
    int b = 11;
    int chuckSize = 3;

    int count = largeArrayRangeCount.largeCountInRange(sortedArray, a, b, chuckSize);
    System.out.println("Count of elements in range [" + a + ", " + b + ") is: " + count);
  }

}
