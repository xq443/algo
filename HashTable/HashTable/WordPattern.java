package HashTable;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {
  public boolean wordPattern(String pattern, String s) {
    String[] words = s.split(" ");
    if(pattern.length() != words.length) return false;
    Map<Character, String> map = new HashMap<>();
    for(int i = 0; i < pattern.length(); i++) {
      char c = pattern.charAt(i);
      String word = words[i];
      if(map.containsKey(c)) {
        if(!map.get(c).equals(word)) return false;
      } else if (map.containsValue(word)) return false;
      else map.put(c, word);
    }
    return true;
  }

  public static void main(String[] args) {
    WordPattern wordPattern = new WordPattern();
    String pattern = "abba", s = "dog dog dog dog";
    System.out.println(wordPattern.wordPattern(pattern, s));
  }
}
