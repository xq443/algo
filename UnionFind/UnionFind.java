import java.util.Arrays;

public class UnionFind {
    int[] parent;
    int[] rank;
    public UnionFind(int num){
        parent = new int[num];
        rank = new int[num];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    public boolean union(int a, int b){
        int rootA = root(a);
        int rootB = root(b);
        if(rootA == rootB) return false;
        if(rank[rootA] > rank[rootB]){
            parent[rootB] = rootA;
        }else if(rank[rootA] < rank[rootB]){
            parent[rootA] = rootB;
        }else{
            parent[rootA] = rootB;
        }
        return true;
    }
    public int root(int a){
        if(a == parent[a]) return a;
        parent[a]= root(parent[a]);
        return parent[a];
    }
}
