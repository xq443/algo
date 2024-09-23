public class CapacityToShipPackagesWithinDDays {
  public int shipWithinDays(int[] weights, int days) {
    int left = 0, right = 0;
    for(int m : weights) {
      left = Math.max(left, m); // ship as a whole, no splitting of packages is allowed
      right += m;  // the sum of weights
    }

    while(left < right) {
      int mid = left + (right - left) / 2;
      if(count(mid, weights) <= days) {
        right = mid;
      } else left = mid + 1;
    }
    return right;
  }

  // capacity is mid, the days it takes to finish loading
  private int count(int capacity, int[] weights) {
    int days = 1, count = 0; // need at least 1 day to load
    for(int weight : weights) {
      count += weight;
      if(count > capacity) {
        days++;
        count = weight; // start another shipping
      }
    }
    return days;
  }

  public static void main(String[] args) {
    CapacityToShipPackagesWithinDDays capacityToShipPackagesWithinDDays =
        new CapacityToShipPackagesWithinDDays();
    int[] weights = {1,2,3,4,5,6,7,8,9,10};
    int days = 5;
    System.out.println(capacityToShipPackagesWithinDDays.shipWithinDays(weights,days));
  }
}
