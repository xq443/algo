package Array;

import java.util.ArrayList;
import java.util.List;

public class PalindromePermutationII {
  public List<String> generatePalindromes(String s) {
    List<String> ret = new ArrayList<>();
    if(s == null || s.length() == 0) return ret;
    // set up a dictionary to store the frequency
    int[] freq = new int[26];
    for(char ch :  s.toCharArray()) {
      freq[ch - 'a']++;
    }

    // check if the odd frequency is > 1
    int oddCount = 0;
    char oddChar = 'a';
    for(int i = 0; i < 26; i++) {
      if(freq[i] % 2 == 1) {
        oddCount++;
        oddChar = (char)(i + 'a');
      }
    }
    if(oddCount > 1) return ret;
    String base = "";
    int len = s.length();
    if(oddCount == 1) {
      base += oddChar;
      len--;
      freq[oddChar - 'a']--;
    }

    generatePalindromes_dfs(ret, len, freq, base);
    return ret;
  }

  private void generatePalindromes_dfs(List<String> ret, int len, int[] freq, String base) {
    if(len == 0) {
      ret.add(base);
      return;
    }
    for (int i = 0; i < 26; i++) {
      if(freq[i] == 0) continue;
      freq[i] -= 2;
      len -= 2;
      char temp = (char)(i + 'a');
      generatePalindromes_dfs(ret, len, freq, temp + base + temp);
      freq[i] += 2;
      len += 2;
    }
  }

  public static void main(String[] args) {
    PalindromePermutationII palindromePermutationII = new PalindromePermutationII();
    String s = "aabb";
    System.out.println(palindromePermutationII.generatePalindromes(s));
  }
}
