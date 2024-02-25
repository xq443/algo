import java.util.*;

public class accountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UnionFind uf = new UnionFind(accounts.size());
        Map<String, Integer> emailToAcc = new HashMap<>();
        for(int i = 0; i < accounts.size(); i++){
            List<String> details = accounts.get(i);
            for(int j = 1; j < details.size(); j++){
                String email = details.get(j);
                if(emailToAcc.containsKey(email)){
                    uf.union(i, emailToAcc.get(email));
                }else{
                    emailToAcc.put(email, i);
                }
            }
        }
        Map<Integer, List<String>> accToEmail = new HashMap<>();
        for(String email : emailToAcc.keySet()){
            int root = uf.root(emailToAcc.get(email));
            if(!accToEmail.containsKey(root)){
                accToEmail.put(root, new ArrayList<>());
            }
            accToEmail.get(root).add(email);
        }
        List<List<String>> ret = new ArrayList<>();
        for(int key : accToEmail.keySet()){
            List<String> detail = accToEmail.get(key);
            Collections.sort(detail);
            String name = accounts.get(key).get(0);
            detail.add(0, name);
            ret.add(detail);
        }
        return ret;
    }
    public static void main(String[] args) {
        accountsMerge a = new accountsMerge();
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
