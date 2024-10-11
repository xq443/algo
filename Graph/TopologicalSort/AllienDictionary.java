import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AllienDictionary {
  public String alienOrder(String[] words) {
    List<List<Integer>> adjList = new ArrayList<>();
    int[] inDegree = new int[26];
    Arrays.fill(inDegree, -1);

    for(String word : words) {
      for(char c : word.toCharArray()) {
        inDegree[c - 'a'] = 0;
      }
    }
    for(int i = 0; i < 26; i++) {
      adjList.add(new ArrayList<>());
    }

    // Build adjacency list and update in-degrees
    for(int i = 0; i < words.length - 1; i++) {
      String word1 = words[i];
      String word2 = words[i+1];
      int flag = 0;
      for (int m = 0, n = 0; m < word1.length() && n < word2.length(); m++, n++) {
        if(word1.charAt(m) != word2.charAt(n)) {
          adjList.get(word1.charAt(m) - 'a').add(word2.charAt(n) - 'a');
          inDegree[word2.charAt(n) - 'a'] ++;
          flag = 1;
          break;
        }
      }

      // word2 is prefix of word1
      if(flag == 0 && (word1.length()) > (word2.length())) return "";
    }
    StringBuilder ret = new StringBuilder();
    int total = 0;
    Queue<Integer> queue = new LinkedList<>();

    // Initialize queue with nodes having zero in-degree
    for(int i = 0; i < 26; i++) {
      if(inDegree[i] == 0){
        queue.add(i);
      }
      if(inDegree[i] != -1) total++;
    }

    //perform topological sort
    while(!queue.isEmpty()) {
      int curr = queue.poll();
      ret.append((char)(curr + 'a'));

      for(int next : adjList.get(curr)) {
        inDegree[next]--;
        if(inDegree[next] == 0){
          queue.add(next);
        }
      }
    }

    // If all characters are visited, return the result
    return (total == ret.length()) ? ret.toString() : "";
  }

  public static void main(String[] args) {

    String[] words = {"wrt","wrf","er","ett","rftt"};
    AllienDictionary allienDictionary = new AllienDictionary();
    System.out.println(allienDictionary.alienOrder(words));
  }
}
