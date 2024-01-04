import java.util.ArrayList;
import java.util.List;

public class productofTwoRunLengthEncodedArrays {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> ret = new ArrayList<>();
        int i = 0, j = 0;
        while (i < encoded1.length && j < encoded2.length) {
            int val1 = encoded1[i][0], val2 = encoded2[j][0];
            int freq1 = encoded1[i][1], freq2 = encoded2[j][1];
            int prod = val1 * val2;
            int freq = Math.min(freq1, freq2);

            boolean found = false;
            for(List<Integer> pair : ret){
                if(prod == pair.get(0)){
                    pair.set(1, freq + pair.get(1));
                    found = true;
                    break;
                }
            }
            if (!found) {
                List<Integer> temp = new ArrayList<>();
                temp.add(prod);
                temp.add(freq);
                ret.add(temp);
            } else {
                ret.get(ret.size() - 1).set(1, ret.get(ret.size() - 1).get(1) + freq);
            }

            if (freq1 == freq2) {
                i++;
                j++;
            } else if (freq1 < freq2) {
                i++;
                encoded2[j][1] -= freq1;
            } else {
                j++;
                encoded1[i][1] -= freq2;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        productofTwoRunLengthEncodedArrays p = new productofTwoRunLengthEncodedArrays();
        int[][] encoded1 = {{1,3},{2,3},{6,1}}, encoded2 = {{6,3},{2,3}, {1,3}};
        System.out.println(p.findRLEArray(encoded1, encoded2));
    }
}
