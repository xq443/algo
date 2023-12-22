import java.util.*;

public class topKFrequentElements_v4 {
    public int[] topKFrequent(int[] nums, int k) {
        //build frequency hashmap
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //treemap sort the key in asc order
        TreeMap<Integer, List<Integer>> tree = new TreeMap<>();
        for(int num : map.keySet()){
            int freq = map.get(num);
            if(!tree.containsKey(freq)){
                tree.put(freq, new ArrayList<>());
            }
            tree.get(freq).add(num);
        }
        //reverse add the key into ret
        int[] ret = new int[k];
        int idx = 0;
        while(idx < k){
            Map.Entry<Integer, List<Integer>> entry = tree.pollLastEntry();
            List<Integer> v = entry.getValue();
            for(int m : v) {
                ret[idx++] = m;
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        topKFrequentElements_v4 p = new topKFrequentElements_v4();
        int[]nums = {4,1,-1,2,-1,2,3, 1};
        int k = 3;
        System.out.println(Arrays.toString(p.topKFrequent(nums, k)));
    }
}
//TC O(NlogN)
//SC O(N)
