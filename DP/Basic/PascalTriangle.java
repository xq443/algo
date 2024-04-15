package Basic;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

  /**
   * Given an integer numRows, return the first numRows of Pascal's triangle.
   * @param numRows int
   * @return List<List<Integer>>
   */
  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> ret = new ArrayList<>();
    if(numRows <= 0) return ret;

    for (int i = 0; i < numRows; i++) {
      List<Integer> sub = new ArrayList<>();
      for (int j = 0; j <= i; j++) {
        if(j == 0 || j == i) sub.add(1);
        else{
          int num = ret.get(i - 1).get(j) + ret.get(j - 1).get(j - 1);
          sub.add(num);
        }
      }
      ret.add(sub);
    }
    return ret;
  }

  public static void main(String[] args) {
    PascalTriangle pascalTriangle = new PascalTriangle();
    int numRows = 5;
    System.out.println(pascalTriangle.generate(numRows));
  }
}
