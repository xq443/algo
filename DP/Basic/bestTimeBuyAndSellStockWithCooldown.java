package Basic;

public class bestTimeBuyAndSellStockWithCooldown {
    public static int maxProfit(int[] prices){
        if(prices.length == 0 || prices.length == 1) return 0;

        int buy = Integer.MIN_VALUE, sell = 0, sell_cooldown = 0;

        for (int i = 0; i < prices.length; i++) {
            int buy_temp = buy, sell_temp = sell, cooldown_temp = sell_cooldown;

            buy = Math.max(buy_temp, cooldown_temp - prices[i]);
            sell = Math.max(sell_temp, buy_temp + prices[i]);
            sell_cooldown = sell_temp;

        }

        return sell;
    }
    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        int[] prices_1 = {1};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit(prices_1));
    }
}
/**
 * buy-> sell-> sell_cooldown -> buy
 */