import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MaximumFrequencyStack {

  Map<Integer, Integer> map;
  List<Stack<Integer>> bucket;

  /**
   * constructs an empty frequency stack.
   */
  public MaximumFrequencyStack() {
    this.map = new HashMap<>();
    this.bucket = new ArrayList<>();
  }

  /**
   * pushes an integer val onto the top of the stack.
   * @param val
   */
  public void push(int val) {
    map.put(val, map.getOrDefault(val, 0) + 1);
    int freq = map.get(val);

    if (freq > bucket.size()) {
      bucket.add(new Stack<>());
    }
    bucket.get(freq - 1).push(val);
  }

  /**
   * removes and returns the most frequent element in the stack.
   * If there is a tie for the most frequent element,
   * the element closest to the stack's top is removed and returned.
   * @return
   */
  int pop() {
    int freq = bucket.size();
    int ret = bucket.get(freq - 1).pop();
    // the empty stack
    if (bucket.get(freq - 1).isEmpty()) {
      bucket.remove(bucket.size() - 1);
    }
    // the empty map
    if (map.get(ret) == 0) {
      map.remove(ret);
    }
    return ret;
  }

  public static void main(String[] args) {
    MaximumFrequencyStack freqStack = new MaximumFrequencyStack();
    freqStack.push(5);                   // The stack is [5]
    freqStack.push(7);                   // The stack is [5,7]
    freqStack.push(5);                   // The stack is [5,7,5]
    freqStack.push(7);                   // The stack is [5,7,5,7]
    freqStack.push(4);                   // The stack is [5,7,5,7,4]
    freqStack.push(5);                   // The stack is [5,7,5,7,4,5]
    System.out.println(freqStack.pop()); // return 5, as 5 is the most frequent.
                                         // The stack becomes [5,7,5,7,4].
    System.out.println(
        freqStack.pop()); // return 7, as 5 and 7 is the most frequent, but 7 is
                          // closest to the top. The stack becomes [5,7,5,4].
    System.out.println(freqStack.pop()); // return 5, as 5 is the most frequent.
                                         // The stack becomes [5,7,4].
    System.out.println(
        freqStack.pop()); // return 4, as 4, 5 and 7 is the most frequent, but 4
                          // is closest to the top. The stack becomes [5,7].
  }
}

// time complexity: O(1) for the random access of hashing and listing index
// space complexity: O(n）
