package MonotonicStack;

import java.util.Arrays;

public class BuildingsWithAnOceanView {

  /**
   * Given an integer array heights of size n that represents the heights of the buildings in the
   * line.
   * <p>
   * The ocean is to the right of the buildings. A building has an ocean view if the building can
   * see the ocean without obstructions. Formally, a building has an ocean view if all the buildings
   * to its right have a smaller height. Return a list of indices (0-indexed) of buildings that have
   * an ocean view, sorted in increasing order.
   *
   * @param heights
   * @return int[]
   */
  public int[] findBuildings(int[] heights) {
    if(heights == null || heights.length == 0) return heights;
    int maxHeight = 0, idx = heights.length - 1;
    for (int i = heights.length - 1; i >= 0; i--) {
      if(heights[i] > maxHeight) {
        maxHeight = heights[i];
        heights[idx--] = i;
      }
    }
    return Arrays.copyOfRange(heights, idx + 1, heights.length);
    // Stack<Integer> stack = new Stack<>();
    // for(int i = 0; i < heights.length; i++) {
    //     while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
    //         stack.pop();
    //     }
    //     stack.push(i);
    // }
    // int[] ret = new int[stack.size()];
    // for(int i = stack.size() - 1; i >= 0; i--) {
    //     ret[i] = stack.pop();
    // }
    // return ret;
  }

  public static void main(String[] args) {
    BuildingsWithAnOceanView buildingsWithAnOceanView = new BuildingsWithAnOceanView();
    int[] heights = {4,2,3,1};
    System.out.println(Arrays.toString(buildingsWithAnOceanView.findBuildings(heights)));
  }
}
