public class valid_palinrome_II {

    public static boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while(left <= right) {
            if(s.charAt(left) != s.charAt(right)) {
                return isPalindrome (s, left+1, right)
                        || isPalindrome (s, left,right-1);

            }
            left++;
            right--;
        }
        return true;
    }
    private static boolean isPalindrome(String s, int left, int right) {
        while(left <= right) {
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;

        }
        return true;
    }
    public static void main (String[] args) {
        String s = "qxq";
        Boolean Result = validPalindrome(s);

        System.out.println(Result);
    }
}

