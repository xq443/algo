public class nextPermutation {
    public void nextPermutation(int[] nums) {
        if(nums.length == 0 || nums == null) return;
        //1. from right to left, find the index in desc order
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i+1]){
            i--;
        }
        if(i == -1){
            reverse(nums, 0);
            return;
        }
        //2. from right to left, find the first index larger that the benchmark
        int j = nums.length - 1;
        while(j >= 0 && nums[j] <= nums[i]){
            j--;
        }
        //3. swap element i and j
        swap(nums, i, j);
        //4. reverse i +1
        reverse(nums, i+1);
    }
    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    private void reverse(int[] nums, int i){
        int left = i, right = nums.length - 1;
        while(left < right){
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        nextPermutation n = new nextPermutation();
        int[]nums = {1,2,3};

        int[]nums2 = {3,2,1};
        n.nextPermutation(nums);
        n.nextPermutation(nums2);

        for (int num : nums) {
            System.out.println(num);
        }
        for (int j : nums2) {
            System.out.println(j);
        }
    }
}
