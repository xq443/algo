package Basic;

import java.util.ArrayList;
import java.util.List;

public class Triangle {
  /**
   * Given a triangle array, return the minimum path sum from top to bottom.
   * If you are on index i on the current row,
   * you may move to either index i or index i + 1 on the next row.
   * @param triangle List<List<Integer>>
   * @return int
   */
  public int minimumTotal(List<List<Integer>> triangle) {
    if(triangle == null || triangle.size() == 0) return 0;
    for (int i = triangle.size() - 2; i >= 0 ; i--) {
      List<Integer> sub = triangle.get(i);
      for (int j = 0; j <= i; j++) {
        // Get the values of the two adjacent elements in the next row
        int num1 = triangle.get(i + 1).get(j); // Directly below
        int num2 = triangle.get(i + 1).get(j + 1); // Diagonally below

        // Update the current element to store the minimum path sum up to that point
        sub.set(j, Math.min(num2, num1) + sub.get(j));
      }
    }
    return triangle.get(0).get(0);
  }

  public static void main(String[] args) {
    Triangle triangle = new Triangle();
    List<List<Integer>> t = new ArrayList<>();
    t.add(new ArrayList<>());
    t.add(new ArrayList<>());
    t.add(new ArrayList<>());
    t.add(new ArrayList<>());
    t.get(0).add(2);
    t.get(1).add(3); t.get(1).add(4);
    t.get(2).add(6); t.get(2).add(5); t.get(2).add(7);
    t.get(3).add(4); t.get(3).add(1); t.get(3).add(8); t.get(3).add(3);
    System.out.println(triangle.minimumTotal(t));
  }
}
