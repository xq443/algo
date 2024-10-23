package String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeandDecodeStrings {

  public String encode(List<String> strs) {
    StringBuilder sub = new StringBuilder();
    for (String str : strs) {
      sub.append(str.length()).append("&").append(str);
    }
    return sub.toString();
  }

  public List<String> decode(String s) {
    List<String> res = new ArrayList<>(); // 5&jacob5&jacob
    int i = 0;
    while (i < s.length()) {
      int j = i;
      while (j < s.length() && s.charAt(j) != '&')
        j++;
      int length = Integer.parseInt(s.substring(i, j));
      String target = s.substring(j + 1, j + 1 + length);
      res.add(target);
      i = j + 1 + length;
    }
    return res;
  }

  public static void main(String[] args) {
    EncodeandDecodeStrings encodeandDecodeStrings =
        new EncodeandDecodeStrings();
    List<String> strs = Arrays.asList("hello", "cathyqin");
    System.out.println(encodeandDecodeStrings.encode(strs));
    System.out.println(
        encodeandDecodeStrings.decode(encodeandDecodeStrings.encode(strs)));
  }
}
