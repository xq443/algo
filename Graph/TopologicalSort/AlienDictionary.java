import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AlienDictionary {
  public String alienOrder(String[] words) {
    if(words == null || words.length == 0) return "";

    // build up graph
    List<List<Integer>> graph = new ArrayList<>();
    for(int i = 0; i < 26; i++){
      graph.add(new ArrayList<>());
    }

    // build up inDegree
    int[] inDegree = new int[26];
    Arrays.fill(inDegree, -1);
    for(String word : words){
      for(char c : word.toCharArray()){
        inDegree[c - 'a'] = 0;
      }
    }

    // add elements dependency into the graph
    for(int i = 0; i < words.length - 1; i++){
      String first = words[i];
      String second = words[i + 1];
      int flag = 0;

      for(int m = 0, n = 0; m < first.length() && n < second.length(); m++, n++){
        if(first.charAt(m) != second.charAt(n)){
          graph.get(first.charAt(m) - 'a').add(second.charAt(n) - 'a');
          inDegree[second.charAt(n) - 'a'] ++;
          flag = 1;
          break;
        }
      }
      if(flag == 0 && first.length() > second.length()) return "";
    }

    // initialize queue
    Queue<Integer> queue = new LinkedList<>();
    StringBuilder sb = new StringBuilder();
    int total = 0;

    for(int i = 0; i < 26; i++){
      if(inDegree[i] == 0){
        queue.offer(i);
      }
      if(inDegree[i] != -1){
        total++;
      }
    }

    // bfs
    while(!queue.isEmpty()){
      int cur = queue.poll();
      sb.append((char)(cur + 'a'));
      for(int next : graph.get(cur)){
        inDegree[next]--;
        if(inDegree[next] == 0){
          queue.offer(next);
        }
      }
    }
    return (sb.length() == total) ? sb.toString() : "";
  }

  public static void main(String[] args) {
    AlienDictionary alienDictionary = new AlienDictionary();
    String[] test1 = {"wrt","wrf","er","ett","rftt"};
    System.out.println(alienDictionary.alienOrder(test1));
  }
}
