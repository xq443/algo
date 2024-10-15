package BinarySearchTree;

class Solution2 {
  public static int solution(int[] points, String tokens) {
    if(points == null || tokens == null || points.length == 0 || tokens.isEmpty()) return 0;
    if(points.length > tokens.length()) throw new RuntimeException("points array length should be greater or equal to tokens.");
    int ret = 0;
    int n = points.length;
    for(int i = 0; i < n; i++) {
      char x = tokens.charAt(i);
      if (x != 'T') continue;
      ret += points[i];
      if (i == 0) continue;
      if (tokens.charAt(i - 1) == 'T') ret += 1;
    }
    return ret;
  }

  public static void main(String[] args) {
    String s = "";
    int[] arr = new int[]{};
    System.out.println(solution(arr,""));
  }
}