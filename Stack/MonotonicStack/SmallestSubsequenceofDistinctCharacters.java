package MonotonicStack;

import java.util.Stack;

public class SmallestSubsequenceofDistinctCharacters {
  /**
   * @param s
   * @return the lexicographically smallest subsequence of
   * s that contains all the distinct characters of s exactly once.
   *
   * Input: s = "bcabc"
   * Output: "abc"
   * Example 2:
   *
   * Input: s = "cbacdcbc"
   * Output: "acdb"
   */
  public String smallestSubsequence(String s) {
    // 1. monotonic stack: if stack.peek() > curr, then pop()
    // 2. create a frequency array to record, pop precondition : frequency > 0
    // 3. exactly once, create a flag/ boolean array to mark if visited already
    // Stack<Character> stack = new Stack<>();
    int[] frequency = new int[26];
    for(int i = 0; i < s.length(); i++){
      frequency[s.charAt(i) - 'a']++;
    }
    boolean[] visited = new boolean[26];
    StringBuilder ret = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char curr = s.charAt(i);
      frequency[curr - 'a']--;
      if(visited[curr - 'a']) continue;

      while(!ret.isEmpty() && ret.charAt(ret.length() -1) - 'a' > curr - 'a' && frequency[ret.charAt(ret.length() -1) - 'a'] > 0){
        visited[ret.charAt(ret.length() -1) - 'a'] = false;
        ret.deleteCharAt(ret.length() - 1);
      }
      visited[curr - 'a'] = true;
      ret.append(curr);

    }
//    while(!stack.isEmpty()) {
//      ret.insert(0, stack.pop());
//    }
    return ret.toString();

  }

  public static void main(String[] args) {
    SmallestSubsequenceofDistinctCharacters smallestSubsequenceofDistinctCharacters = new SmallestSubsequenceofDistinctCharacters();
    System.out.println(smallestSubsequenceofDistinctCharacters.smallestSubsequence("cbacdcbc"));
  }

}
