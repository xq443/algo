package Basic;

public class BestTimeBuyAndSellStockWithCoolDown {
    public int maxProfit(int[] prices){
        // j- 1. buy  sell cooldown
        // j.    buy  sell cooldown
        if(prices.length == 0 || prices.length == 1) return 0;
        int buy = Integer.MIN_VALUE, sell = 0, hold = 0; // hold status only occurs after sell
        for (int price : prices) {
            int buy_temp = buy, sell_temp = sell, hold_temp = hold;
            // buy <== buy cooldown
            // cooldown <== sell
            // sell <== buy sell
            buy = Math.max(buy_temp, hold_temp - price);
            sell = Math.max(sell_temp, buy_temp + price);
            hold = sell_temp;
        }
        return sell;
    }
    public static void main(String[] args) {
        BestTimeBuyAndSellStockWithCoolDown bestTimeBuyAndSellStockWithCoolDown =
            new BestTimeBuyAndSellStockWithCoolDown();
        int[] prices = {1,2,3,0,2};
        int[] prices_1 = {1};
        System.out.println(bestTimeBuyAndSellStockWithCoolDown.maxProfit(prices));
        System.out.println(bestTimeBuyAndSellStockWithCoolDown.maxProfit(prices_1));
    }
}