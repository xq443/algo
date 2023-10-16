import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class intersection_of_two_arrays_3 {
    public static int[] intersection(int[] nums1, int[] nums2) {
        //use two hashset to filter out the same value;
        //use the retailAll api to find the intersection of the two hashsets
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for(int i: nums1) {
            set1.add(i);
        }
        for(int i: nums2) {
            set2.add(i);
        }

        set1.retainAll(set2);

        int[] res = new int[set1.size()];
        int k = 0;
        for(int num: set1) {
            res[k++] = num;
        }
        return res;
    }
    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5,6,4};
        int[] arr2 = {1,2,4,5,7,8};

        System.out.println(Arrays.toString(intersection(arr1,arr2)));
    }
}
//time complexity O(M+N) on average
//space complexity O(M+N)