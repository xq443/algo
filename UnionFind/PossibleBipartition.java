
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PossibleBipartition {
  public List<List<Integer>> graphBuild(int n, int[][] dislikes){
    List<List<Integer>> adjList = new ArrayList<>(n+1);
    for (int i = 0; i <= n; i++) {
      adjList.add(new ArrayList<>());
    }
    for(int[] dislike : dislikes){
      int person1 = dislike[0];
      int person2 = dislike[1];
      adjList.get(person2).add(person1);
      adjList.get(person1).add(person2);
    }
    return adjList;
  }
  public boolean possibleBipartition_bfs(int n, int[][] dislikes) {
    // build up graph
    List<List<Integer>> adjList = graphBuild(n, dislikes);
    Queue<Integer> queue = new LinkedList<>();
    int[] group = new int[n + 1];
    for (int i = 1; i <= n ; i++) {
      if(group[i] == 0){ // unassigned
        group[i] = 1;
        queue.offer(i);
        while(!queue.isEmpty()){
          int curr = queue.poll();
          for(int next : adjList.get(curr)){
            if(group[next] == group[curr]) return false;
            if(group[next] == 0){
              group[next] = (group[curr] == 1) ? 2 : 1;
              queue.offer(next);
            }
          }
        }
      }
    }
    return true;
  }

  public boolean possibleBipartition_UnionFind(int n, int[][] dislikes) {
    // build up graph
    List<List<Integer>> adjList = graphBuild(n, dislikes);
    UnionFind unionfind = new UnionFind(n + 1);
    // union the adj nodes
    for(List<Integer> adj : adjList) {
      if(adj.isEmpty()) continue;
      int curr = adj.get(0);
      for(int next : adj){
        unionfind.union(curr, next);
      }
    }
    // if the union nodes exist in dislike, return false
    for(int[] dislike : dislikes) {
      int person1 = dislike[0];
      int person2 = dislike[1];
      if(unionfind.root(person2) == unionfind.root(person1)) return false;
    }
    return true;
  }

  public static void main(String[] args) {
    int n = 4;
    int[][]dislikes = {{1,2},{1,3},{2,4}};
    PossibleBipartition possibleBipartition = new PossibleBipartition();
    System.out.println(possibleBipartition.possibleBipartition_bfs(n, dislikes));
    System.out.println(possibleBipartition.possibleBipartition_UnionFind(n, dislikes));
  }

}
