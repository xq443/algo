import java.util.Scanner;

public class arrayAdd {
    public static void main(String[] args) {

        //1. initialize int[] arr = {1,2,3};
        //2. define a new arr int[] arrNew = new int[arr.length + 1]
        //3. loop arr, copy the elements onto arrNew
        //4. arrNew[arrNew.length - 1] = 4;
        //5. let arr points out to arrNew
        //6. create a scanner to let user input
        //7. not sure when the user will lot out, we will use do-while + break to control;
        int[] arr = {1,2,3};

        do{
            int[] arrNew = new int[arr.length + 1];
            for(int i = 0; i < arr.length; i++) {
                arrNew[i] = arr[i];
            }

            Scanner myscanner = new Scanner(System.in);
            System.out.println("Please enter the element: ");
            int addNum = myscanner.nextInt();
            arrNew[arrNew.length - 1] = addNum;
            arr = arrNew;

            for(int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
            System.out.println("If you want to add? y/n");
            char key = myscanner.next().charAt(0);
            if(key == 'n') {
                System.out.println("You ended the program.");
                break;
            }

        }
        while(true);
    }
}
