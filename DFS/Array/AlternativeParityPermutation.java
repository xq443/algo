package Array;

import java.util.ArrayList;
import java.util.List;

public class AlternativeParityPermutation {

  /*
   * Complete the 'alternatingParityPermutations' function below.
   *
   * The function is expected to return a 2D_INTEGER_ARRAY.
   * The function accepts INTEGER n as parameter.
   */
  public List<List<Integer>> alternatingParityPermutations(int n) {
    List<List<Integer>> ret = new ArrayList<>();
    int[] visited = new int[n + 1]; // record if the number has been used or not
    int[] sub = new int[n]; // record the current number
    for(int i = 1; i <= n; i++) {
      visited[i] = 1; // mark as used
      sub[0] = i; // the position num is i
      dfs(0, i % 2, n, visited, sub, ret); // recursion
      visited[i] = 0; // backtracking
    }
    return ret;
  }
  private void dfs(int x, int e, int n, int[] visited, int[] sub, List<List<Integer>> ret) {
    if(x == n - 1) { // boundary
      List<Integer> now = new ArrayList<>();
      for (int j : sub) now.add(j); // add the current number into sub
      ret.add(now);
      return;
    }
    for(int i = 1; i <= n; i++) {
      if(i % 2 != e && visited[i] == 0) { // must not equal to the previous odd or even and not be used
        visited[i] = 1; // mark as used
        sub[x + 1] = i; // record the position
        dfs(x + 1, i % 2, n, visited, sub, ret);
        visited[i] = 0; // backtracking
      }
    }
  }

  public static void main(String[] args) {
    AlternativeParityPermutation alternativeParityPermutation = new AlternativeParityPermutation();
    int n = 4;
    System.out.println(alternativeParityPermutation.alternatingParityPermutations(n));
  }
}

