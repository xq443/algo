import java.util.Scanner;

public class arrayReduce {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        do{
            int[] arrNew = new int[arr.length - 1];
            for(int i = 0; i < arrNew.length; i++) {
                arrNew[i] = arr[i];
            }
            arr = arrNew;
            for (int j : arr) {
                System.out.println(j);
            }
            Scanner myscanner = new Scanner(System.in);
            System.out.println("If you want to reduce the array again? y/n");
            char key = myscanner.next().charAt(0);
            if(key == 'n') {
                break;
            }
            if(arr.length == 0) {
                System.out.println("The length of array is 0.");
                break;
            }
        }
        while (true);
    }
}
