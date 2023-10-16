public class binary_search {
    public static int search(int[] num, int target) {
        int left = 0, right = num.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(num[mid] == target) return index = mid;
            else if(num[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return index;
    }

    public static void main (String[] args) {
        int[] arr = {1,2,3,4};
        int target = 4;
        System.out.println(search(arr,target));
    }
}
