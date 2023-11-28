public class make_String_a_Subsequence_Using_Cyclic_increments {
    public boolean canMakeSubsequence(String str1, String str2){
        int i = 0, j = 0;
        while(i < str1.length() && j < str2.length()){
            if(str1.charAt(i) == str2.charAt(j)){
                i++;
                j++;
            }else{
                char next = (char)((str1.charAt(i) - 'a' + 1) % 26 + 'a');
                if(next == str2.charAt(j)){
                    i++;
                    j++;
                }else {
                    i++;
                }
            }
        }
        return j == str2.length();
    }
    public static void main(String[] args){
        make_String_a_Subsequence_Using_Cyclic_increments makeStringASubsequenceUsingCyclicIncrements = new make_String_a_Subsequence_Using_Cyclic_increments();
        String str1 = "abc", str2 = "ad";
        System.out.println(makeStringASubsequenceUsingCyclicIncrements.canMakeSubsequence(str1,str2));
    }
}
