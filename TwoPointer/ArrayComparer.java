import java.util.HashSet;
import java.util.Set;

public class ArrayComparer {
  public static boolean compare(int[] a, int[] b, int[] ignore) {
    Set<Integer> set = new HashSet<>();
    for(int num : ignore) {
      set.add(num);
    }
    int i = 0, j = 0;
    while(i < a.length && j < b.length) {
      while(i < a.length && set.contains(a[i])) {
        i++;
      }

      while(j < b.length && set.contains(b[j])) {
        j++;
      }

      if(i < a.length && j < b.length) {
        if(a[i] != b[j]) return false;
        i++;
        j++;
      }
    }

    // After matching, skip any trailing ignored elements
    while(i < a.length && set.contains(a[i])) {
      i++;
    }
    while(j < b.length && set.contains(b[j])) {
      j++;
    }

    // If both pointers have reached the end, the arrays match
    return (i == a.length && j == b.length);
  }

  public static void main(String[] args) {
    System.out.println(compare(new int[]{1, 4, 5, 2, 3}, new int[]{1, 5, 3}, new int[]{2, 4})); // true
    System.out.println(compare(new int[]{1, 4, 5, 2, 3}, new int[]{1, 3, 5}, new int[]{2, 4})); // false
    System.out.println(compare(new int[]{1, 2, 3, 4, 5}, new int[]{1, 3, 5}, new int[]{2, 4})); // true
  }
}
