package MonotonicStack;

import java.util.Stack;

public class LargestRectangleInHistogram {

  /**
   * Given an array of integers heights representing the histogram's bar height
   * where the width of each bar is 1, return the area of the largest rectangle in the histogram.
   * @param heights
   * @return
   */
  public int largestRectangleArea(int[] heights) {
    if(heights == null || heights.length == 0) return 0;
    int ret = 0;
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i <= heights.length; i++) {
      int x = (i < heights.length) ? heights[i] : 0;
      while(!stack.isEmpty() && heights[stack.peek()] > x) {
        int currIdx = stack.pop();
        int currHeight = heights[currIdx];
        int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
        ret = Math.max(ret, currHeight * width);
      }
      stack.push(i);
    }
    return ret;
  }

  public static void main(String[] args) {
    LargestRectangleInHistogram largestRectangleInHistogram = new LargestRectangleInHistogram();
    int[] heights = {2,1,5,6,2,3};
    System.out.println(largestRectangleInHistogram.largestRectangleArea(heights));
  }
}
