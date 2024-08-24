package HashTable;
public class customSortString {
    public String customSortString(String order, String s) {
        int [] charfrequency = new int [26];
        for(char ch : s.toCharArray()){
            charfrequency[ch - 'a'] ++;
        }
        //append the char to ret
        StringBuilder ret = new StringBuilder();
        for(char ch : order.toCharArray()){
            int cnt = charfrequency[ch - 'a'];
            ret.append(String.valueOf(ch).repeat(Math.max(0, cnt)));
            charfrequency[ch -'a'] = 0;
        }
        //append the rest char to ret
        for(int i = 0; i < 26; i++){
            for (int j = 0; j < charfrequency[i]; j++) {
                ret.append((char)(i + 'a'));
            }
        }
        return ret.toString();
    }
    public static void main(String[] args) {
        customSortString c = new customSortString();
        String x=  "cba", y = "aaabccd";
        System.out.println(c.customSortString(x, y));
    }
}
