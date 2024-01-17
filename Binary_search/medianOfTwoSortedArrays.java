public class medianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int a = nums1.length, b = nums2.length;
        int len = a + b;
        if(b < a) return findMedianSortedArrays(nums2, nums1);
        int aStart = 0, aEnd = a;
        int aCut, bCut;

        while(aStart <= aEnd){
            aCut = (aStart + aEnd) / 2;
            bCut = (len + 1) / 2 - aCut;
            double aLeft = (aCut == 0) ? Integer.MIN_VALUE : nums1[aCut - 1];
            double aRight = (aCut == a) ? Integer.MAX_VALUE : nums1[aCut];
            double bLeft = (bCut == 0) ?  Integer.MIN_VALUE : nums2[bCut - 1];
            double bRight = (bCut == 0) ?  Integer.MAX_VALUE : nums2[bCut];
            if(aLeft > bRight) aEnd = aCut - 1;
            else if(aRight < bLeft) aStart = aCut + 1;
            else{
                if(len % 2 == 0){
                    return ((Math.max(aLeft, bLeft) + Math.min(aRight, bRight)) / 2);
                }else{
                    return Math.max(aLeft, bLeft);
                }
            }
        }
        return  -1;
    }
    public static void main(String[] args) {
        medianOfTwoSortedArrays f = new medianOfTwoSortedArrays();
        int[] nums1 = {2,4,6,7,10};
        int[] nums2 = {1,3,5,8,9,11,12,13};
        System.out.println(f.findMedianSortedArrays(nums1, nums2));
    }
}
//TC O(logn)
//SC O(1)
