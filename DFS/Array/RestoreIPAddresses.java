package Array;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
  public List<String> restoreIpAddresses(String s) {
    if(s == null || s.length() == 0) return new ArrayList<>();
    List<String> ret = new ArrayList<>();
    restoreIpAddresses_dfs(ret, new StringBuilder(), 0, 0, s);
    return ret;
  }

  private void restoreIpAddresses_dfs(List<String> ret, StringBuilder sb, int index, int counter, String s) {
    if (counter == 4 && index == s.length()) {
      ret.add(sb.toString());
      return;
    } else if (counter == 4 || index == s.length())
      return;

    for (int len = 1; len <= 3; len++) {
      if (index + len > s.length())
        break;
      String segment = s.substring(index, index + len);
      if (restoreIpAddresses_isValid(segment)) {
        int prevLength = sb.length();
        if (counter > 0)
          sb.append('.');
        sb.append(segment);
        restoreIpAddresses_dfs(ret, sb, index + len, counter + 1, s);
        sb.setLength(prevLength);
      }
    }
  }
  private boolean restoreIpAddresses_isValid(String segments) {
    if(segments.charAt(0) == '0' && segments.length() > 1) return false;
    int num = Integer.parseInt(segments);
    return num >= 0 && num <= 255;
  }

  public static void main(String[] args) {
    RestoreIPAddresses restoreIPAddresses = new RestoreIPAddresses();
    String s = "25525511135";
    System.out.println(restoreIPAddresses.restoreIpAddresses(s));
  }
}
