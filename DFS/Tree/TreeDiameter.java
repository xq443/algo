package Tree;

import java.util.LinkedList;
import java.util.List;

public class TreeDiameter {
    int ret = 0;
    public int treeDiameter(int[][] edges) {
        int n = edges.length +1;
        List[] graph = new List[n]; // array of list to show the adjacency list
        //index of graph represents the node
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList();
        }
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        dfs_treeDiameter(graph, 0, -1);
        return ret;
    }
    private int dfs_treeDiameter(List[] graph, int curr, int parent){
        int firstM = 0, secondM = 0;
        if(graph[curr] != null){
            for(Object element : graph[curr]){
                if((Integer) element == parent) continue;;
                int len = dfs_treeDiameter(graph, (Integer) element, curr);
                if(len > firstM){
                    secondM = firstM;
                    firstM = len;
                }else if(len > secondM){
                    secondM = len;
                }
            }
            ret = Math.max(ret, firstM + secondM);
        }
        return 1 +  Math.max(firstM, secondM);
    }
    public static void main(String[] args) {
        TreeDiameter t = new TreeDiameter();
        int[][] edges = {{0,1},{0,2}};
        int[][] edges1 = {{0,1},{1,2},{2,3},{1,4},{4,5}};
        System.out.println(t.treeDiameter(edges));
        System.out.println(t.treeDiameter(edges1));

    }
}
