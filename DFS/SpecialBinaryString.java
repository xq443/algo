import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpecialBinaryString {
  public String makeLargestSpecial(String S) {
    List<String> res = new ArrayList<>();

    // Variable to track the balance between '1's and '0's
    int count = 0, i = 0;

    // Iterate through the string to find special substrings
    for (int j = 0; j < S.length(); ++j) {
      // Increment count for '1', decrement for '0'
      if (S.charAt(j) == '1') count++;
      else count--;

      // If the substring from i to j is a valid special string
      if (count == 0) {
        // Recursively process the substring and add '1' and '0' around it
        res.add('1' + makeLargestSpecial(S.substring(i + 1, j)) + '0');
        // Update i to the next character after the current substring
        i = j + 1;
      }
    }

    // Sort the substrings in reverse order (largest lexicographically first)
    res.sort(Collections.reverseOrder());

    // Join and return the final result
    return String.join("", res);
  }

  public static void main(String[] args) {
    SpecialBinaryString s = new SpecialBinaryString();
    System.out.println(s.makeLargestSpecial("11011000"));
  }
}
