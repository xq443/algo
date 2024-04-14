package MonotonicStack;

import java.util.Stack;

public class NumberOfVisiblePeopleInQueue {

  /**
   * the ith person can see the jth person if i < j and min(heights[i], heights[j]) >
   *  max(heights[i+1], heights[i+2], ..., heights[j-1]).
   *
   * Return an array answer of length n where answer[i] is the number of people
   * the ith person can see to their right in the queue.
   * @param A
   * @return int[]
   */
  public int[] canSeePersonsCount(int[] A) {
    int[] ret = new int[A.length];
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < A.length; i++) {
      // if the last element in stack <= A[i] which means only one people stack.pop() can see
      while(!stack.isEmpty() && A[stack.peek()] <= A[i]) {
        ret[stack.pop()]++;
      }
      // increment the ret[stack.peek()] by 1 when we figure out it is greater than one element
      if(!stack.isEmpty()) {
        ret[stack.peek()]++;
      }
      stack.push(i);
    }
    return ret;
  }
  public static void main(String[] args) {
    NumberOfVisiblePeopleInQueue numberofVisiblePeopleinaQueue = new NumberOfVisiblePeopleInQueue();
    int[] heights = {10,6,8,5,11,9};
    int[] ret = numberofVisiblePeopleinaQueue.canSeePersonsCount(heights);
    for (int i = 0; i < ret.length; i++) {
      System.out.println(ret[i]);
    }
  }
}
