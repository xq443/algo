import java.util.*;

public class topKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        //build frequency hashmap
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<List<Integer>> bucket = new ArrayList<>();
        for(int i = 0; i < nums.length + 1; i++){
            bucket.add(new ArrayList<>());
        }
        //insert frequency and corresponding num into bucket
        for(int num :  map.keySet()){
            int frequency = map.get(num);
            bucket.get(frequency).add(num);
        }
        //extract top k frequency num
        int[] ret = new int[k];
        int index = 0;
        for (int i = bucket.size() - 1; i >= 0; i--) {
            if(bucket.get(i).size() == 0) continue;
            for(int num : bucket.get(i)){
                if (k > 0) {
                    ret[index++] = num;
                    k--;
                }
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        topKFrequentElements t = new topKFrequentElements();
        int[]nums = {4,1,-1,2,-1,2,3};
        int k = 2;
        System.out.println(Arrays.toString(t.topKFrequent(nums, k)));
    }
}
//TC O(N)
//SC O(N)