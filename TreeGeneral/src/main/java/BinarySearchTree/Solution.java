package BinarySearchTree;

import java.util.HashSet;
import java.util.Set;

class Solution {

  static int ret = 0;
  static Set<Integer> st = new HashSet<>(); // for the purpose of removing duplication

  // x is the number of digit, total is the accumulative sum, s is the target, sum is the current sum
  public static void dfs(int x, int total, int s, int sum) {
    if (x == 4) { // if we have considered four digits
      if (total == s && !st.contains(
          sum)) { // then to check if the accumulative sum is equal to the target
        ret++;
        st.add(sum); // add to the set, we won't visit it again

        return;
      }
      for (int i = 0; i <= 9; i++) {
        dfs(x + 1, total + i, s, sum * 10 + i); // depth first search to the next digit
      }
    }
  }

    public static int solution(int S) {
      ret = 0;
      st = new HashSet<>();
      dfs(0,0,S,0); // initialize the dfs parameters
      return ret;
    }
  }
