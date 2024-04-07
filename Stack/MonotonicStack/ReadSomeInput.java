package MonotonicStack;

import java.util.Scanner;

public class ReadSomeInput {
    public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      System.out.print("How many courses are you taking this term? ");
      int numCourses = console.nextInt();
      System.out.println(numCourses + "... That's too many!");
    }
  }

