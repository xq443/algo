import java.util.*;

public class TopKFrequentElements_v2 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }

        // corner case: if there is only one number in nums, we need the bucket has index 1.
        List<List<Integer>> bucket = new ArrayList<>();
        for(int i = 0; i < nums.length + 1; i++){
            bucket.add(new ArrayList<>());
        }
        for(int num : map.keySet()){
            int frequency = map.get(num);
            bucket.get(frequency).add(num);
        }

        List<Integer> res = new ArrayList<>();
        for(int i=bucket.size()-1; i>0 && k>0; i--){
            if(bucket.get(i).size() != 0){
                List<Integer> list = bucket.get(i);
                for(int num : list){
                    if(k > 0){
                        res.add(num);
                        k--;
                    }
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        TopKFrequentElements_v2 t = new TopKFrequentElements_v2();
        int[]nums = {4,1,-1,2,-1,2,3, 1};
        int k = 2;
        System.out.println(t.topKFrequent(nums, k));
    }
}
//TC O(N)
//SC O(N)