package HashTable;

import java.util.HashMap;
import java.util.Map;
public class customSortString_v2 {
    public String customSortString_2(String order, String s) {
        //create a map to reflect key: char value: frequency
        Map<Character, Integer> map  = new HashMap<>();
        for(char ch : s.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        //append the chars in ret
        StringBuilder ret = new StringBuilder();
        for(char c : order.toCharArray()){
            if(map.containsKey(c)){
                int cnt = map.get(c);
                while(cnt > 0){
                    ret.append(c);
                    cnt--;
                }
                map.remove(c);
            }
        }
        //append the rest char in ret
        for(char rest : map.keySet()){
            int cont = map.get(rest);
            while(cont-- > 0){
                ret.append(rest);
            }
        }
        return ret.toString();
    }
    public static void main(String[] args){
        customSortString_v2 c = new customSortString_v2();
        String x=  "cba", y = "aaabccd";
        System.out.println(c.customSortString_2(x, y));
    }
}
