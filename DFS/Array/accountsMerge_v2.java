package Array;

import java.util.*;

public class accountsMerge_v2 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            String name = accounts.get(i).get(0);
            Set<String> neighbour = new HashSet<>(accounts.get(i));
            neighbour.remove(name);

            for(int j = 1; j < accounts.get(i).size(); j++){
                String email = accounts.get(i).get(j);
                if(!graph.containsKey(email)){
                    graph.put(email, new HashSet<>());
                }
                graph.get(email).addAll(neighbour);
                emailToName.put(email, name);
            }
        }
        Set<String> visited = new HashSet<>();
        List<List<String>> ret = new ArrayList<>();
        //DFS search the graph
        for(String email : graph.keySet()){
            if(!visited.contains(email)){
                List<String> sub = new ArrayList<>();
                dfs_accountsMerge(sub, graph, email, visited);
                Collections.sort(sub);
                sub.add(0, emailToName.get(email));
                ret.add(sub);
            }
        }
        return ret;
    }
    public void dfs_accountsMerge(List<String> sub,Map<String, Set<String>> graph, String email,Set<String> visited){
        sub.add(email);
        visited.add(email);
        for(String neighbour : graph.get(email)){
            if(!visited.contains(neighbour)) dfs_accountsMerge(sub, graph, neighbour, visited);
        }
    }
    public static void main(String[] args) {
        accountsMerge_v2 a = new accountsMerge_v2();
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));

        List<List<String>> mergedAccounts = a.accountsMerge(accounts);
        System.out.println("Merged Accounts:");
        for (List<String> account : mergedAccounts) {
            System.out.println(account);
        }
    }
}
