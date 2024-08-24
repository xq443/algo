package HashTable;

import java.util.Arrays;

public class ReorganizeString {

  /**
   * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
   * Return any possible rearrangement of s or return "" if not possible.
   * @param s
   * @return String
   */
  public String reorganizeString(String s) {
    if(s == null || s.length() == 0) return "";

    // build up frequency map
    int[] frequency = new int[26];
    for(char c : s.toCharArray()) {
      frequency[c - 'a']++;
    }

    // get the maxFrequency and its corresponding index
    int maxFrequency = 0, position = 0;
    for(int i = 0; i < frequency.length; i++) {
      if(maxFrequency < frequency[i]){
        maxFrequency = frequency[i];
        position = i;
      }
    }

    // edge case
    if(maxFrequency > (s.length() + 1) / 2) return "";

    // fill out the positions where index is even
    int index = 0;
    char[] ret = new char[s.length()];
    while (frequency[position] != 0) {
      ret[index] = (char) ('a' + position);
      index += 2;
      frequency[position]--;
    }

    // fill out odd index position
    for (int i = 0; i < frequency.length ; i++) {
      while(frequency[i] > 0) {
        if (index >= s.length()) index = 1;
        ret[index] = (char) ('a' + i);
        index += 2;
        frequency[i]--;
      }
    }
    return Arrays.toString(ret);
  }

  public static void main(String[] args) {
    ReorganizeString reorganizeString = new ReorganizeString();
    String s = "vvvlo";
    System.out.println(reorganizeString.reorganizeString(s));
  }
}
