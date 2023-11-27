import java.util.Scanner;

public class arrayAddExercise {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};

        do{
            int[] arrNew = new int[arr.length + 1];
            for(int i = 0; i < arr.length; i++) {
                arrNew[i] = arr[i];
            }
            Scanner myscanner = new Scanner(System.in);
            System.out.println("Please enter the added number:");
            int addNum = myscanner.nextInt();
            arrNew[arrNew.length - 1] = addNum;

            arr = arrNew;
            for(int j: arr) {
                System.out.println(j);
            }
            System.out.println("If you want to add the number? y/n");
            char key = myscanner.next().charAt(0);
            if(key == 'n') {
                System.out.println("You've loged out the program.");
                break;
            }
        } while(true);
    }
}
