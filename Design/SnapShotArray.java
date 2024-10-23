import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SnapShotArray {
  List<TreeMap<Integer, Integer>> list;
  int snapId;

  /**
   * initializes an array-like data structure with the given length.
   * Initially, each element equals 0.
   * @param length
   */
  public SnapShotArray(int length) {
    this.list = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      list.add(new TreeMap<>());
    }
    this.snapId = 0;
  }

  /**
   * sets the element at the given index to be equal to val
   * @param index
   * @param val
   */
  public void set(int index, int val) { list.get(index).put(snapId, val); }

  /**
   * takes a snapshot of the array and returns the snap_id:
   * the total number of times we called snap() minus 1.
   * @return
   */
  public int snap() {
    int ret = snapId;
    snapId++;
    return ret;
  }

  /**
   * returns the value at the given index,
   * at the time we took the snapshot with the given snap_id
   * @param index
   * @param snapId
   * @return
   */
  public int get(int index, int snapId) {
    TreeMap<Integer, Integer> map = list.get(index);
    // finds the entry in the map whose key is the greatest (closest to snapId)
    // but less than or equal to the negative snapId
    Map.Entry<Integer, Integer> entry = map.floorEntry(snapId);
    return entry.getValue();
  }

  public static void main(String[] args) {
    SnapShotArray snapShotArray = new SnapShotArray(3);
    snapShotArray.set(1, 5);
    System.out.println(snapShotArray.snap()); // 0
    snapShotArray.set(1, 10);
    System.out.println(snapShotArray.snap());    // 1
    System.out.println(snapShotArray.get(1, 0)); // 5
    System.out.println(snapShotArray.get(1, 3)); // 10
  }
  // Index 0: {}
  // Index 1: {0: 5, 1: 10}   // Snapshot ID: 0, Value: 5; Snapshot ID: 1,
  // Value: 10 Index 2: {}
}
