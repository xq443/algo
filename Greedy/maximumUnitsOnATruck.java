import java.util.Arrays;

public class maximumUnitsOnATruck {
    public int maximumUnits(int[][] boxTypes, int truckSize){
        Arrays.sort(boxTypes, (a,b) -> Integer.compare(b[1], a[1]));
        int total = 0;
        for(int[] box : boxTypes){
            int count = Math.min(box[0], truckSize);
            total += count * box[1];
            truckSize -= count;
            if(truckSize == 0) return  total;
        }
        return total;
    }
    public static void main(String[] args) {
        maximumUnitsOnATruck maximumUnitsOnATruck = new maximumUnitsOnATruck();
        int[][] boxTypes_1 = {{1,3},{2,2},{3,1}};
        int truckSize_1 = 4;
        System.out.println(maximumUnitsOnATruck.maximumUnits(boxTypes_1, truckSize_1));
        int[][] boxTypes_2 = {{5,10},{2,5},{4,7},{3,9}};
        int truckSize_2 = 10;
        System.out.println(maximumUnitsOnATruck.maximumUnits(boxTypes_2, truckSize_2));
    }
}
//TC O(NLOGN)
//SC O(1)