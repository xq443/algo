import java.util.Random;

public class randomPickWithWeight {
    int[] prefixS;
    int[] w;
    int target;
    Random rand;

    public randomPickWithWeight(int[] w){
        this.w = w;
        this.rand = new Random();
        this.prefixS = new int[w.length];
        prefixS[0] = w[0];
        for (int i = 1; i < prefixS.length; i++) {
            prefixS[i] = prefixS[i-1] + w[i];
        }
    }
    public String pickIndex() {
        int totalSum = prefixS[prefixS.length - 1];
        target = rand.nextInt(prefixS[prefixS.length - 1]) + 1;
        //binary search
        int left = 0, right = prefixS.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;

            if(prefixS[mid] == target) {
                double probability = (double) w[mid] / totalSum;
                return "index = " + mid + " prob = " + probability;
            }
            else if (prefixS[mid] < target) {
                left = mid;
            }else right = mid -1;
        }
        double probability = (double) w[right] / totalSum;
        return "index = " + right + " prob = " + probability;
    }
    public static void main(String[] args) {
        int[] test = {1,2,3,4};
        randomPickWithWeight randomPickWithWeight = new randomPickWithWeight(test);
        System.out.println(randomPickWithWeight.pickIndex());
    }
}
