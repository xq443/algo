package HashTable;

public class verifyinganAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        int[] charMap = new int[26];
        for (int i = 0; i < order.length(); i++) {
            charMap[order.charAt(i) - 'a'] = i;
        }
        for(int i = 0; i < words.length - 1; i++){
            if(!AlienSorted(words[i], words[i+1], charMap)) return false;
        }
        return true;
    }
    private boolean AlienSorted(String words1, String words2, int[] charMap){
        for(int i = 0; i < Math.min(words2.length(), words1.length()); i++){
            if(words1.charAt(i) == words2.charAt(i)) continue;
            return charMap[words1.charAt(i) - 'a'] <= charMap[words2.charAt(i) - 'a'];
        }
        return words1.length() <= words2.length();
    }

    public static void main(String[] args) {
        verifyinganAlienDictionary v = new verifyinganAlienDictionary();
        String[] words = {"hello","leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println(v.isAlienSorted(words,order));
    }
}
