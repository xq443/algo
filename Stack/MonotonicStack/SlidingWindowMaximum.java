package MonotonicStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMaximum {
  public int[] maxSlidingWindow(int[] nums, int k) {
    Deque<int[]> maxWindow = new ArrayDeque<>();
    int[] ret = new int[nums.length - k + 1];
    int idx = 0;
    for (int i = 0; i < nums.length; i++) {
      // out of boundary
      if(!maxWindow.isEmpty() && i - k == maxWindow.peekFirst()[0]) {
        maxWindow.pollFirst();
      }
      // if nums[i] is greater
      while(!maxWindow.isEmpty() && nums[i] > maxWindow.peekLast()[1]){
        maxWindow.pollLast();
      }
      maxWindow.offerLast(new int[]{ i, nums[i]});

      // record
      if(i >= k - 1) {
        assert maxWindow.peekFirst() != null;
        ret[idx++] = maxWindow.peekFirst()[1];
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
    int[]nums = {1,3,-1,-3,5,3,6,7};
    int k = 3;
    int[] ret = slidingWindowMaximum.maxSlidingWindow(nums, k);
    System.out.println(Arrays.toString(ret));
  }
}
//Time Complexity: O(n)
//Space Complexity: O(n)
