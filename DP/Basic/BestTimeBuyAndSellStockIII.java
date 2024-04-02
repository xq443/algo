package Basic;

public class BestTimeBuyAndSellStockIII {
    public int maxProfit(int[] prices){
        // J - 1  SELL1 BUY1 SELL2  BUY2
        // J      BUY1  SELL1 SELL2 BUY2
        if(prices.length == 0 || prices.length == 1) return 0;
        int buy_1 = Integer.MIN_VALUE, buy_2 = Integer.MIN_VALUE;
        int sell_1 = 0, sell_2 = 0;

        for (int price : prices) {
            int buy1_temp = buy_1, buy2_temp = buy_2;
            int sell1_temp = sell_1, sell2_temp = sell_2;
            buy_1 = Math.max(buy1_temp, -price);
            sell_1 = Math.max(sell1_temp, buy1_temp + price);
            buy_2 = Math.max(buy2_temp, sell1_temp - price);
            sell_2 = Math.max(sell2_temp, buy2_temp + price);
        }
        return Math.max(sell_1, sell_2);
    }
    public static void main(String[] args) {
        BestTimeBuyAndSellStockIII bestTimeBuyAndSellStockIII = new BestTimeBuyAndSellStockIII();
        int[] prices = {3,3,5,0,0,3,1,4};
        int[] prices_1 = {1,2,3,4,5};
        System.out.println(bestTimeBuyAndSellStockIII.maxProfit(prices));
        System.out.println(bestTimeBuyAndSellStockIII.maxProfit(prices_1));
    }
}