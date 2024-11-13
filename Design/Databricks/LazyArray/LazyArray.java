package Databricks.LazyArray;

import java.util.*;
import java.util.function.Function;

public class LazyArray {
  private int[] arr; // Original array
  private List<Function<Integer, Integer>> fns; // List of functions
  private Function<Integer, Integer> combinedFn; // Cached combined function
  private boolean isChanged; // Flag to indicate if the function chain has changed

  // Constructor
  public LazyArray(int[] a) {
    this.arr = a;
    this.fns = new ArrayList<>();
    this.combinedFn = x -> x; // Initial combined function is identity function
    this.isChanged = false;
  }

  // constructor for chaining
  private LazyArray(int[] a, List<Function<Integer, Integer>> fs) {
    this.arr = a;
    this.fns = new ArrayList<>(fs);
    this.combinedFn = x -> x; // Initial combined function is identity function
    this.isChanged = true; // New function chain needs to be recombined
  }

  // map method: add a function to the chain and return a new LazyArray
  public LazyArray map(Function<Integer, Integer> function) {
    List<Function<Integer, Integer>> newFs = new ArrayList<>(this.fns);
    newFs.add(function);
    return new LazyArray(this.arr, newFs);
  }

  // indexOf method: apply all functions and return the index of the target value
  public int indexOf(int value) {
    // Recombine functions if the chain has changed
    if (isChanged) {
      combinedFn = x -> x;
      for (Function<Integer, Integer> f : fns) {
        // this method creates a new function that first applies combinedFn and then applies f.
        combinedFn = combinedFn.andThen(f);
      }
      isChanged = false; // Mark as clean after updating the cache
    }
    // Traverse the array and apply the combined function
    for (int i = 0; i < arr.length; i++) {
      if (combinedFn.apply(arr[i]).equals(value)) {
        // apply the combined function to the i-th element of the array and
        // check if the result is equal to the specified value.
        return i;
      }
    }
    return -1; // Not found
  }

  public static void main(String[] args) {
    // Test case 1: Basic functionality
    LazyArray a1 = new LazyArray(new int[]{10, 20, 30, 40, 50});
    System.out.println(a1.map(x -> x * 2).indexOf(40)); // Expected output: 1

    // Test case 2: Multiple maps
    LazyArray a2 = new LazyArray(new int[]{10, 20, 30, 40, 50});
    System.out.println(a2.map(x -> x * 2).map(x -> x * 3).indexOf(240)); // Expected output: 3

    // Edge case 1: Empty array
    LazyArray a3 = new LazyArray(new int[]{});
    System.out.println(a3.map(x -> x + 1).indexOf(1)); // Expected output: -1

    // Edge case 2: Value not present
    LazyArray a4 = new LazyArray(new int[]{1, 2, 3});
    System.out.println(a4.map(x -> x * 2).indexOf(10)); // Expected output: -1

    // Edge case 3: Multiple same values
    LazyArray a5 = new LazyArray(new int[]{5, 10, 10, 20});
    System.out.println(a5.map(x -> x).indexOf(10)); // Expected output: 1

    // Edge case 4: Negative numbers
    LazyArray a6 = new LazyArray(new int[]{-10, -20, -30});
    System.out.println(a6.map(x -> x * -1).indexOf(20)); // Expected output: 1

    // Edge case 5: Zero
    LazyArray a7 = new LazyArray(new int[]{0, 1, 2});
    System.out.println(a7.map(x -> x + 0).indexOf(0)); // Expected output: 0

    // Edge case 6: Large array
    int size = 1000000;
    int[] largeArr = new int[size];
    for (int i = 0; i < size; i++) {
      largeArr[i] = i;
    }
    LazyArray a8 = new LazyArray(largeArr);
    System.out.println(a8.map(x -> x + 1).indexOf(999999)); // Expected output: 999998


    // Edge 7: lazy evaluation
    LazyArray a9 = new LazyArray(new int[]{1, 2, 3});
    a9 = a9.map(x -> {
      System.out.println("Function 1 applied");
      return x * 2;
    });
    a9 = a9.map(x -> {
      System.out.println("Function 2 applied");
      return x + 1;
    });
    System.out.println(a9.indexOf(5)); // 3 5 10 预期输出: Function 1 applied, Function 2 applied, 1
  }
}
//map Method:
//Time Complexity: O(1)
//Space Complexity: O(m)

//indexOf Method:
//Time Complexity: O(n)
//Space Complexity: O(1)