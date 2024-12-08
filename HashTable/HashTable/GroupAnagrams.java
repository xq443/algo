package HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String str : strs) {
      char[] chars = str.toCharArray();
      Arrays.sort(chars);
      String key = new String(chars);
      if (!map.containsKey(key)) {
        map.put(key, new ArrayList<>());
      }
      map.get(key).add(str);
    }
    return new ArrayList<>(map.values());
  }

  public static void main(String[] args) {
    GroupAnagrams g = new GroupAnagrams();
    String[] strs = {"eat","tea","tan","ate","nat","bat"};
    System.out.println(g.groupAnagrams(strs));
  }
}
