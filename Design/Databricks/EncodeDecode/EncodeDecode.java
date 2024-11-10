package Databricks.EncodeDecode;

import java.util.ArrayList;
import java.util.List;

public class EncodeDecode {

  static class TypeA {
    int number;
    int count;

    TypeA(int number, int count) {
      this.number = number;
      this.count = count;
    }

    @Override
    public String toString() {
      return "(" + number + "," + count + ")";
    }
  }

  static class TypeB {
    List<Integer> numbers;

    TypeB(List<Integer> numbers) {
      this.numbers = numbers;
    }

    @Override
    public String toString() {
      return numbers.toString();
    }
  }

  public List<Object> encoding(int[] dataStream) {
    List<Object> encoded = new ArrayList<>();
    int n = dataStream.length;
    int i = 0;

    while (i < n) {
      int count = 1;
      while (i + count < n && dataStream[i] == dataStream[i + count]) {
        count++;
      }

      if (count >= 8) {
        encoded.add(new TypeA(dataStream[i], count));
        i += count;
      } else {
        List<Integer> temp = new ArrayList<>();
        for (int j = 0; j < 8 && i < n; j++, i++) {
          temp.add(dataStream[i]);
        }
        encoded.add(new TypeB(temp));
      }
    }

    return encoded;
  }

  public int[] decoding(List<Object> encodedStream) {
    List<Integer> decoded = new ArrayList<>();

    for (Object obj : encodedStream) {
      if (obj instanceof TypeA) {
        TypeA typeA = (TypeA) obj;
        for (int i = 0; i < typeA.count; i++) {
          decoded.add(typeA.number);
        }
      } else if (obj instanceof TypeB) {
        TypeB typeB = (TypeB) obj;
        decoded.addAll(typeB.numbers);
      }
    }

    return decoded.stream().mapToInt(i -> i).toArray();
  }

  public static void main(String[] args) {
    EncodeDecode solution = new EncodeDecode();

    // Test case 1: All elements are the same but less than 8
    int[] dataStream1 = {1, 1, 1, 1, 1, 1, 1};
    List<Object> encoded1 = solution.encoding(dataStream1);
    System.out.println("Encoded (Test case 1): " + encoded1);
    int[] decoded1 = solution.decoding(encoded1);
    System.out.println("Decoded (Test case 1): " + java.util.Arrays.toString(decoded1));

    // Test case 2: All elements are the same and exactly 8
    int[] dataStream2 = {2, 2, 2, 2, 2, 2, 2, 2};
    List<Object> encoded2 = solution.encoding(dataStream2);
    System.out.println("Encoded (Test case 2): " + encoded2);
    int[] decoded2 = solution.decoding(encoded2);
    System.out.println("Decoded (Test case 2): " + java.util.Arrays.toString(decoded2));

    // Test case 3: All elements are the same and more than 8
    int[] dataStream3 = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
    List<Object> encoded3 = solution.encoding(dataStream3);
    System.out.println("Encoded (Test case 3): " + encoded3);
    int[] decoded3 = solution.decoding(encoded3);
    System.out.println("Decoded (Test case 3): " + java.util.Arrays.toString(decoded3));

    // Test case 4: No repeating elements
    int[] dataStream4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    List<Object> encoded4 = solution.encoding(dataStream4);
    System.out.println("Encoded (Test case 4): " + encoded4);
    int[] decoded4 = solution.decoding(encoded4);
    System.out.println("Decoded (Test case 4): " + java.util.Arrays.toString(decoded4));

    // Test case 5: Mixed repeating and non-repeating elements
    int[] dataStream5 = {4, 4, 4, 4, 4, 4, 4, 4, 5, 6, 7, 8, 9, 9, 9, 9, 9, 9, 9, 9};
    List<Object> encoded5 = solution.encoding(dataStream5);
    System.out.println("Encoded (Test case 5): " + encoded5);
    int[] decoded5 = solution.decoding(encoded5);
    System.out.println("Decoded (Test case 5): " + java.util.Arrays.toString(decoded5));

    // Test case 6: Single element
    int[] dataStream6 = {7};
    List<Object> encoded6 = solution.encoding(dataStream6);
    System.out.println("Encoded (Test case 6): " + encoded6);
    int[] decoded6 = solution.decoding(encoded6);
    System.out.println("Decoded (Test case 6): " + java.util.Arrays.toString(decoded6));

    // Test case 7: Empty array
    int[] dataStream7 = {};
    List<Object> encoded7 = solution.encoding(dataStream7);
    System.out.println("Encoded (Test case 7): " + encoded7);
    int[] decoded7 = solution.decoding(encoded7);
    System.out.println("Decoded (Test case 7): " + java.util.Arrays.toString(decoded7));
  }
}
