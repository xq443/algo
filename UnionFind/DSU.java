import java.util.Arrays;

public class DSU {
  int[] parent;
  int[] rank;
  int count;

  public DSU(int n) {
    this.parent = new int[n];
    this.rank = new int[n];
    this.count = 0;
//    for(int i = 0; i < n; i++) {
//      this.parent[i] = i;
//      this.rank[i] = 1;
//    }
    Arrays.fill(parent, -1);
  }

  public int findParent(int x) {
    if(parent[x] != x) {
      parent[x] = findParent(parent[x]);
    }
    return parent[x];
  }

  public void union(int x, int y) {
    int rootX = findParent(x);
    int rootY = findParent(y);
    if(rootX != rootY) {
      if(rank[rootX] > rank[rootY]) {
        parent[rootY] = rootX;
      } else if(rank[rootX] < rank[rootY]) {
        parent[rootX] = rootY;
      } else
        parent[rootY] = rootX;
        rank[rootX]++;
      count--;
    }
  }

  public int getCount() {
    return this.count;
  }

  public void addNew(int x) {
    if(parent[x] == -1) {
      parent[x] = x;
      this.count++;
    }
  }
}
