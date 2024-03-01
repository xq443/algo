import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class NumberofProvinces {
  public int findCircleNum_UnionFind(int[][] isConnected) {
    UnionFind unionFind = new UnionFind(isConnected.length);
    for (int i = 0; i < isConnected.length; i++) {
      for (int j = i + 1; j < isConnected.length; j++) {
        if(isConnected[i][j] ==  1) unionFind.union(i, j);
      }
    }
    return unionFind.getNum();
  }

  public class UnionFind{
    int parent[];
    int num;

    public UnionFind(int n) {
      this.parent = new int[n];
      for(int i = 0; i < n; i++){
        parent[i] = i;
      }
      this.num = n;
    }
    public boolean union(int a, int b){
      int rootA = root(a);
      int rootB = root(b);
      if(rootA == rootB) return false;
      num--;
      parent[rootA] = rootB;
      return true;
    }
    public int root(int a){
      if(a == parent[a]) return a;
      parent[a] = root(parent[a]);
      return parent[a];
    }

    public int getNum() {
      return num;
    }
  }

  public int findCircleNum_bfs(int[][] isConnected) {
    boolean[] visited = new boolean[isConnected.length];
    Queue<Integer> queue = new LinkedList<>();
    int ret = 0;
    for (int i = 0; i < isConnected.length; i++) {
      if(!visited[i]) {
        queue.offer(i);
        while(!queue.isEmpty()) {
          int curr = queue.poll();
          visited[curr] = true;
          for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[curr][j] == 1 && !visited[j]) {
              queue.offer(j);
            }
          }
        }
        ret++;
      }
    }
    return ret;
  }
  public int findCircleNum_dfs(int[][] isConnected) {
    boolean[] visited = new boolean[isConnected.length];
    int ret = 0;
    for (int i = 0; i < isConnected.length; i++) {
      if(!visited[i]) {
        dfsfindCircleNum(isConnected, i, visited);
        ret++;
      }
    }
    return ret;
  }
  public void dfsfindCircleNum(int[][] isConnected, int curr, boolean[] visited){
    for (int i = 0; i < isConnected.length; i++) {
      if(isConnected[curr][i] == 1 && !visited[i]){
        visited[i] = true;
        dfsfindCircleNum(isConnected, i, visited);
      }
    }
  }


  public static void main(String[] args) {
    int[][] test = {{1,1,0},{1,1,0},{0,0,1}};
    NumberofProvinces numberofProvinces = new NumberofProvinces();
    System.out.println(numberofProvinces.findCircleNum_UnionFind(test));
    System.out.println(numberofProvinces.findCircleNum_bfs(test));
    System.out.println(numberofProvinces.findCircleNum_dfs(test));
  }

}
