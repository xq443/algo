public class arrayReverse2 {
    public static void main(String[] args) {
        int[] arr = {11,22,33,44,55,66};
        int[] arr2 = new int[arr.length];
        for(int i = arr.length - 1, j = 0; i >= 0 ; i--, j++) {
            arr2[j] = arr[i];
        }
        //arr2 is a reversed array.
        //let arr point to arr2. The previous arr has no variables, so it will be deleted as trash
        arr = arr2;

        for(int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }
}
