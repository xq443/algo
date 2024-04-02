package Basic;

public class PaintHouse {
  public int minCost(int[][] costs) {
    // J - 1  1/2  0/2   1/3
    // J      0    1     2
    if(costs == null || costs.length == 0) return 0;
    int blue = 0, red = 0, green = 0;
    for(int[] cost : costs) {
      int blue_temp = blue, red_temp = red, green_temp = green;
      blue = Math.min(red_temp + cost[1], green_temp + cost[1]);
      green = Math.min(red_temp + cost[2], blue_temp + cost[2]);
      red = Math.min(blue_temp + cost[0], green_temp + cost[0]);
    }
    return Math.min(Math.min(blue, green), red);
  }

  public static void main(String[] args) {
    PaintHouse paintHouse = new PaintHouse();
    int[][] costs= {{17,2,17},{16,16,5},{14,3,19}};
    int[][] costs1 = {{7,6,2}};
    System.out.println(paintHouse.minCost(costs));
    System.out.println(paintHouse.minCost(costs1));
  }
}
