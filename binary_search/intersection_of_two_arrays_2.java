import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class intersection_of_two_arrays_2 {
    public static int[] intersection(int[] nums1, int[] nums2) {
        //hashset and two pointers
        int i = 0, j = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> intersect = new HashSet<>();
        while (i < nums1.length - 1 && j < nums2.length - 1) {
            if(nums1[i] == nums2[j]) {
                intersect.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]){
                i++;
            } else j++;
        }
        int[] res = new int[intersect.size()];
        int k = 0;
        for(int num: intersect) {
            res[k++] = num;
        }
        return res;
    }
    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5,5,8};
        int[] arr2 = {1,6,8,3,5};
        System.out.println(Arrays.toString(intersection(arr1,arr2)));
    }
}

//time complexity O(M+N)
//space complexity 0(N)