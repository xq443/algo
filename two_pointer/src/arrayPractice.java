import java.util.Scanner;

public class arrayPractice {
    public static void main(String[] args) {
        double[] scores;
        scores = new double[5];
        Scanner myScanner = new Scanner(System.in);
        for(int i = 0; i < scores.length; i++) {
            System.out.println("Please enter the " + (i + 1) + " element.");
            scores[i] = myScanner.nextDouble();
        }

        for(int i = 0; i < scores.length; i++) {
            System.out.println("The "+ (i + 1) + " element is: " + scores[i]);
        }
    }
}
