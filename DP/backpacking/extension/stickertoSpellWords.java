package extension;

import java.util.Arrays;

class stickerstoSpellWords {
    public int minStickers(String[] stickers, String target) {
        int n = target.length();
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int state = 0; state < (1<<n); state++){
            if(dp[state] == Integer.MAX_VALUE )continue;
            for(String sticker : stickers){
                int new_state = helper(target, state, sticker);
                dp[new_state] = Math.min(dp[new_state], dp[state] + 1);

            }
        }
        return (dp[(1 << n)-1] == Integer.MAX_VALUE)? -1 : dp[(1 << n)-1];
    }
    public int helper(String target,int state, String sticker){
        int n = target.length();
        for(char ch : sticker.toCharArray()){
            for(int k = 0; k < n; k++){
                if(((state >> k) & 1) ==0  && target.charAt(k) == ch){
                    state = state + (1 << k);
                    break;
                }
            }
        }
        return state;
    }

    public static void main(String[] args) {

    }
}