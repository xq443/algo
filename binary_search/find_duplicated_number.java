public class find_duplicated_number {
    public static int findDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        int duplicate = -1;
        while(left <= right) {
            int mid = left + (right - left) / 2;

            int count = 0;
            for(int num: nums) {
                if(num <= mid) count++;
            }
            if(count > mid) {
                duplicate = mid;
                right = mid -1;
            }else {
                left = mid + 1;
            }
        } return duplicate;
    }
    public static void main (String[] args) {
        int[] arr = {4,2,1,3,1};
        System.out.println(findDuplicate(arr));
    }
}
//{1,1,2,3,4}