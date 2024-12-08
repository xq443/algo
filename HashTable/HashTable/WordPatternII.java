package HashTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPatternII {
  public boolean wordPatternMatch(String pattern, String s) {
    Map<Character, String> map = new HashMap<>();
    Set<String> set = new HashSet<>();
    return isMatch(0, 0, pattern, s, map, set);
  }

  private boolean isMatch(int patternIdx, int sIdx, String pattern, String s,
      Map<Character, String> map, Set<String> set) {
    if(patternIdx == pattern.length() && sIdx == s.length()) return true;
    if(patternIdx == pattern.length() || sIdx == s.length()) return false;

    char curr = pattern.charAt(patternIdx);
    if(map.containsKey(curr)) {
      String word = map.get(curr);
      if(!s.startsWith(word, sIdx)) return false;
      return isMatch(patternIdx + 1, sIdx + word.length(), pattern, s, map, set);
    }
    for(int i = sIdx; i < s.length(); i++) {
      String temp = s.substring(sIdx, i + 1);
      if(set.contains(temp)) continue;
      set.add(temp);
      map.put(curr, temp);
      if(isMatch(patternIdx + 1, i + 1, pattern, s, map, set)) return true;
      set.remove(temp);
      map.remove(curr);
    }
    return false;
  }

  public static void main(String[] args) {
    WordPatternII wordPatternII = new WordPatternII();
    String pattern = "abab", s = "redblueredblue";
    System.out.println(wordPatternII.wordPatternMatch(pattern, s));
  }
}
