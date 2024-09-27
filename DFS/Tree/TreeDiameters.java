package Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeDiameters {
  int farthestNode;
  int maxDis;

  List<List<Integer>> adjList;
  public int treeDiameter(int[][] edges) {
    // adjList
    adjList = new ArrayList<>();
    int n = edges.length + 1;
    for(int i = 0; i < n; i++) {
      adjList.add(new ArrayList<>());
    }
    for(int[] edge : edges) {
      int start = edge[0];
      int end = edge[1];
      adjList.get(start).add(end);
      adjList.get(end).add(start);
    }

    maxDis = 0;
    farthestNode = 0;
    // from the root node
    farthest_dfs(0, -1, 0);

    maxDis = 0;
    farthest_dfs(farthestNode, -1, 0);
    return maxDis;
  }
  private void farthest_dfs(int node, int parent, int dis) {
    if(dis > maxDis) {
      maxDis = dis;
      farthestNode = node;
    }
    for(int neighbor : adjList.get(node)) {
      if(neighbor != parent) {
        farthest_dfs(neighbor, node, dis + 1);
      }
    }
  }

  public static void main(String[] args) {
    TreeDiameters treeDiameters = new TreeDiameters();
    int[][] edges = {{0,1},{0,2}};
    System.out.println(treeDiameters.treeDiameter(edges));
  }
}
