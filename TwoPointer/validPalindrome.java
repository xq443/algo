public class validPalindrome {
    public static boolean validPalindrome(String s){
        //base case
        if(s.isEmpty()) return true;

        //initialize pointer
        int left = 0;
        int right = s.length() - 1;

        while(left < right){
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);

            if(!Character.isLetterOrDigit(leftChar)) left++;
            else if(!Character.isLetterOrDigit(rightChar)) right--;
            else{
                if(Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) return false;
                else{
                    left++;
                    right--;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "A MAN, A PLAn, A CANAL: PANAMA";

        System.out.println(validPalindrome(s));

    }
}
