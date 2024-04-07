import java.util.*;

public class topKFrequentElements_v3 {
    public int[] topKFrequent(int[] nums, int k) {
        //build frequency hashmap
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //set priority queue in des order of value
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((b, a) -> (a.getValue() - b.getValue()));
        //add to queue
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(entry);
        }
        int[] ret = new int[k];
        //extract top k
        int idx = 0;
        for (int i = 0; i < k; i++) {
            ret[idx++] = Objects.requireNonNull(queue.poll()).getKey();
        }
        return ret;
    }

    public static void main(String[] args) {
        topKFrequentElements_v3 p = new topKFrequentElements_v3();
        int[]nums = {4,1,-1,2,-1,2,3, 1};
        int k = 3;
        System.out.println(Arrays.toString(p.topKFrequent(nums, k)));
    }
}
//TC O(NlogN)
//SC O(N)