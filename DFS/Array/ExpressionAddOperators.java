package Array;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
  public List<String> addOperators(String num, int target) {
    List<String> ret = new ArrayList<>();
    if(num == null || num.length() == 0) return ret;
    addOperators_dfs(ret, num, target, 0, 0, "", 0);
    return ret;
  }

  private void addOperators_dfs(List<String> ret, String num, int target, int position,
      long value, String sub, long prev) {
    if(position == num.length() && value == target) {
      ret.add(sub);
      return;
    }

    for (int i = position; i < num.length(); i++) {
      // leading zero
      if(num.charAt(position) == 0 && i != position) break;

      long curr = Long.parseLong(num.substring(position, i + 1));

      if(position == 0) {
        addOperators_dfs(ret, num, target, i + 1, value + curr,
            "" + curr, curr);
      } else {

        addOperators_dfs(ret, num, target, i + 1, value + curr,
            sub + "+" + curr, curr);
        addOperators_dfs(ret, num, target, i + 1, value - curr,
            sub + "-" + curr, -curr);
        addOperators_dfs(ret, num, target, i + 1, value - prev + prev * curr,
            sub + "*" + curr, prev * curr);
      }
      // (7 + 3) + 6    7 + 3 * 6
    }
  }
  // prev is the value of the last operand

  public static void main(String[] args) {
    ExpressionAddOperators expressionAddOperators = new ExpressionAddOperators();
    String num = "123";
    int target = 6;
    System.out.println(expressionAddOperators.addOperators(num, target));
  }
}
