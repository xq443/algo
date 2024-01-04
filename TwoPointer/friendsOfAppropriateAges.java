import java.util.HashMap;
import java.util.Map;

public class friendsOfAppropriateAges {
    public int numFriendRequests(int[] ages) {
        int ret = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int age : ages){
            map.put(age, map.getOrDefault(age, 0) + 1);
        }

        for(int i : map.keySet()){
            for(int j : map.keySet()){
                if(canRequest(i, j)){
                    if(i == j){
                        ret += map.get(i) * (map.get(i) - 1);

                    }else{
                        ret +=  map.get(i) * map.get(j);
                    }
                }
            }
        }
        return ret;
    }
    private boolean canRequest(int i, int j){
        return !((j <= 0.5 * i + 7) || (j > i) || (j > 100 && i < 100));
    }
    public static void main(String[] args) {
        friendsOfAppropriateAges f = new friendsOfAppropriateAges();
        int[] ages = {16,16};
        int[] ages1 = {16,17,18};
        System.out.println(f.numFriendRequests(ages));
        System.out.println(f.numFriendRequests(ages1));
    }

}
