package Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindUniqueBinaryString {
  public String findDifferentBinaryString(String[] nums) {
    if(nums == null || nums.length == 0) return null;
    Set<String> set = new HashSet<>(Arrays.asList(nums));
    return findDifferentBinaryString_dfs(nums, set, new StringBuilder(), 0);
  }
  private String findDifferentBinaryString_dfs(String[] nums, Set<String> set, StringBuilder sub, int index) {
    if(index == nums[0].length()) {
      if(!set.contains(sub.toString())) {
        return sub.toString();
      }
      return null;
    }
    sub.append('0');
    String temp1 = findDifferentBinaryString_dfs(nums, set, sub, index + 1);
    if(temp1 != null) return temp1;
    sub.deleteCharAt(sub.length() - 1); // backtracking

    sub.append('1');
    String temp2 = findDifferentBinaryString_dfs(nums, set, sub, index + 1);
    if(temp2 != null) return temp2;
    sub.deleteCharAt(sub.length() - 1); // backtracking

    return null;
  }

  public static void main(String[] args) {
    FindUniqueBinaryString findUniqueBinaryString = new FindUniqueBinaryString();
    String[] nums = {"00", "11"};
    System.out.println(findUniqueBinaryString.findDifferentBinaryString(nums));
  }
}
