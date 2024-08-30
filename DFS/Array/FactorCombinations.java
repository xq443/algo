package Array;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {
  public List<List<Integer>> getFactors(int n) {
    List<List<Integer>> ret = new ArrayList<>();
    getFactors_dfs(ret, n, new ArrayList<>(), 2);
    return ret;
  }

  private void getFactors_dfs(List<List<Integer>> ret, int n, List<Integer> sub, int index) {
    if(n == 1) {
      if(sub.size() > 1) {
        ret.add(new ArrayList<>(sub));
        return;
      }
    }
    for (int i = index; i <= n ; i++) {
      if(n % i == 0) {
        sub.add(i);
        getFactors_dfs(ret, n / i, sub, i);
        sub.remove(sub.size() - 1);
      }
    }
  }

  public static void main(String[] args) {
    FactorCombinations factorCombinations = new FactorCombinations();
    int n = 12;
    System.out.println(factorCombinations.getFactors(n));
  }
}
