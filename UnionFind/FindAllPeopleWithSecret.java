import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class FindAllPeopleWithSecret {
  public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
    // 1. set up the graph: sort by meeting time in asc order with treemap<Integer, int[]>
    // 2. for each meeting time, union the persons
    // 3. set another for loop, if the persons at certain time does not have the root same as root(0), the reset its root
    Set<Integer> ret = new HashSet<>();
    ret.add(0);
    ret.add(firstPerson);
    TreeMap<Integer, List<int[]>> graph = new TreeMap<>();
    for(int[] meeting :  meetings) {
      graph.putIfAbsent(meeting[2], new ArrayList<>());
      graph.get(meeting[2]).add(new int[]{meeting[0], meeting[1]});
    }

    UnionFind uf = new UnionFind(n);
    uf.union(0, firstPerson);

    for(int time :  graph.keySet()) {
      for(int [] group : graph.get(time)){
        uf.union(group[0], group[1]);
      }
      for(int[] group : graph.get(time)){
        if(uf.root(group[0]) != uf.root(0) &&
          uf.root(group[1]) != uf.root(0)){
          // reset
          uf.reset(group[0]);
          uf.reset(group[1]);
        }else{
          // add it to the result list
          ret.add(group[0]);
          ret.add(group[1]);
        }
      }
    }
    return new ArrayList<>(ret);
  }
  public static class UnionFind{
    int[] parent;

    public UnionFind(int n) {
      this.parent = new int[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }
    public void union(int a, int b){
      int roota = root(a);
      int rootb = root(b);
      if (roota == rootb) return;
      parent[rootb] = roota;
      return;
    }
    public int root(int a){
      if(a == parent[a]) return a;
      parent[a] = root(parent[a]);
      return parent[a];
    }
    public void reset(int a){
      parent[a] = a;
    }
  }

  public static void main(String[] args) {
    /**
     * n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
     * Output: [0,1,2,3,4]
     */
    int n = 5;
    int[][] meetings = {{3,4,2}, {1,2,1},{2,3,1}};
    int firstPerson = 1;
    FindAllPeopleWithSecret findAllPeopleWithSecret = new FindAllPeopleWithSecret();
    List<Integer> ret = findAllPeopleWithSecret.findAllPeople(n, meetings, firstPerson);
    for (int i = 0; i < ret.size(); i++) {
      System.out.println(ret.get(i));
    }
  }
}
