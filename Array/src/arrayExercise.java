public class arrayExercise {
    public  static void main(String[] args) {
        char[] chars = new char[26];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ('A' + i); // type casting
            System.out.println(chars[i]);
        }

    }
}
