public class arrayExercise02 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,85,6,7,8};
        int max = 0, maxIndex = 0;
        for(int i = 0; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
            
        }
        System.out.println("Max=" + max + " maxIndex=" + maxIndex);
    }
}
