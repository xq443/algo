public class maxminumUnitesOnATruck_v2 {
    public int maximumUnits_v2(int[][] boxTypes, int truckSize){
        int[] count = new int[1001];
        for(int[] box : boxTypes){
            count[box[1]] += box[0];
        }
        int total = 0;
        for (int i = 1000; i >= 1 ; i--) {
            if(count[i] > 0){
                int boxes = Math.min(truckSize, count[i]);
                truckSize -= boxes;
                total += boxes * i;
            }
            if(truckSize == 0) break;

        }
        return total;
    }
    public static void main(String[] args) {
        maxminumUnitesOnATruck_v2 maximumUnitsOnATruck2 = new maxminumUnitesOnATruck_v2();
        int[][] boxTypes_1 = {{1,3},{2,2},{3,1}};
        int truckSize_1 = 4;
        System.out.println(maximumUnitsOnATruck2.maximumUnits_v2(boxTypes_1, truckSize_1));
        int[][] boxTypes_2 = {{5,10},{2,5},{4,7},{3,9}};
        int truckSize_2 = 10;
        System.out.println(maximumUnitsOnATruck2.maximumUnits_v2(boxTypes_2, truckSize_2));
    }
}
//TC O(N)
//SC O(1000) = O(1)
