import java.util.Arrays;

public class first_last_position_of_element_in_sorted_array {
    public static int[] searchRange(int[] num, int target) {
        int[] res = new int[2];
        res[0] = findStartingIndex(num,target);
        res[1] = findEndingIndex(num,target);
        return res;
    }
    private static int findStartingIndex(int num[], int target) {
        int start = 0, end = num.length - 1;
        int index = -1;

        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(num[mid] >= target) end = mid - 1;
            else start = mid + 1;

            if(num[mid] == target) index = mid;
        }
        return index;
    }
    private static int findEndingIndex(int num[], int target) {
        int start = 0, end = num.length - 1;
        int index = -1;

        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(num[mid] <= target) start = mid + 1;
            else end = mid - 1;

            if(num[mid] == target) index = mid;
        }
        return index;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,6,8,8,8,10};
        int target = 8;
        System.out.println(Arrays.toString(searchRange(arr, target)));
    }
}
