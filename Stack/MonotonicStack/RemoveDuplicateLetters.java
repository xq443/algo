package MonotonicStack;

import java.util.Stack;

public class RemoveDuplicateLetters {
  public String removeDuplicateLetters(String s) {
    // int[] record the frequency of Character
    // only take care of when not visited
    // monotonic stack: if s.charAt(i) - 'a' < stack.peek(): pop
    int[] charFrequency = new int[26];
    for(int i = 0; i < s.length(); i++){
      charFrequency[s.charAt(i) - 'a']++;
    }
    boolean[] visited = new boolean[26];
    Stack<Character> stack = new Stack<>();

    for(int i = 0; i < s.length(); i++) {

      charFrequency[s.charAt(i) - 'a']--;
      if(visited[s.charAt(i) - 'a']) continue;

      while(!stack.isEmpty() && stack.peek() - 'a' > s.charAt(i) - 'a' &&
          charFrequency[stack.peek() - 'a'] > 0){
        visited[stack.pop() - 'a'] = false;
      }
      stack.push(s.charAt(i));
      visited[s.charAt(i) - 'a'] = true;
    }

    StringBuilder ret = new StringBuilder();
    while(!stack.isEmpty()){
      ret.insert(0, stack.pop());
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    String s1 = "bcabc";
    String s2 = "cbacdcbc";
    RemoveDuplicateLetters removeDuplicateLetters = new RemoveDuplicateLetters();
    System.out.println(removeDuplicateLetters.removeDuplicateLetters(s1));
    System.out.println(removeDuplicateLetters.removeDuplicateLetters(s2));
  }
}
