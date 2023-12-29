public class unionfind {
    int[] parent;
    int[] rank;
    public unionfind(int num){
        parent = new int[num];
        rank = new int[num];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    public void union(int a, int b){
        int rootA = root(a);
        int rootB = root(b);
        if(rootA == rootB) return;
        if(rank[rootA] > rank[rootB]){
            parent[rootB] = rootA;
        }else if(rank[rootA] < rank[rootB]){
            parent[rootA] = rootB;
        }else{
            parent[rootA] = rootB;
        }
    }
    public int root(int a){
        if(parent[a] == a) return a;
        parent[a]= root(parent[a]);
        return parent[a];
    }
}
