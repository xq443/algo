package HashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class TopKFrequentWords {

  /**
   * Given an array of strings words and an integer k,
   * Return the answer sorted by the frequency from highest to lowest.
   * Sort the words with the same frequency by their lexicographical order.
   * @param words
   * @param k
   * @return
   */
  public List<String> topKFrequent(String[] words, int k) {
    List<String> ret = new ArrayList<>();
    if(words == null || words.length == 0 || k == 0) return ret;
    // count frequency for each word
    Map<String, Integer> frequency = new HashMap<>();
    for(String s :  words) {
      frequency.put(s, frequency.getOrDefault(s,0) + 1);
    }
    // set up heap
    PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(
        (a, b) -> {
          if (Objects.equals(b.getValue(), a.getValue())) {
            return a.getKey().compareTo(b.getKey()); // Lexicographical in asc if frequencies are equal
          } else {
            return b.getValue() - a.getValue(); // Frequencies in desc
          }
        }
    );
    // populate heap
    for(Map.Entry<String, Integer> s : frequency.entrySet()) {
      queue.offer(s);
    }
    // add elements into ret list
    for (int i = 0; i < k && !queue.isEmpty(); i++) {
      ret.add(queue.poll().getKey());
    }
    return ret;
  }

  public static void main(String[] args) {
    TopKFrequentWords topKFrequentWords = new TopKFrequentWords();
    String[] words = {"i","love","leetcode","i","love","coding"};
    int k = 2;
    System.out.println(topKFrequentWords.topKFrequent(words, k));
  }
}
