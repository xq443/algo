import java.util.Random;
import java.util.TreeMap;

public class randomPickWithWeight_V2 {
    TreeMap<Integer, Integer> map;
    int sum;
    Random random;
    int [] w;
    public randomPickWithWeight_V2(int[] w) {
        this.sum = 0;
        this.w = w;
        this.map = new TreeMap<>();
        this.random = new Random();
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            map.put(sum, i);
        }
    }
    public int pickIndex() {
        int target = random.nextInt(sum) + 1; //[1, sum]
        return map.get(map.ceilingKey(target));
    }
    public static void main(String[] args) {
        int[] t = {1,2,3,4};
        randomPickWithWeight_V2 m = new randomPickWithWeight_V2(t);
        System.out.println(m.pickIndex());
    }
}
