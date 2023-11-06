package Basic;

public class bestTimeBuyAndSellStockIII {
    public static int maxProfit(int[] prices){
        if(prices.length == 0 || prices.length == 1) return 0;
        int buy_1 = Integer.MIN_VALUE, buy_2 = Integer.MIN_VALUE;
        int sold_1 = 0, sold_2 = 0;

        for(int i = 0; i< prices.length; i++){
            int buy1_temp = buy_1, buy2_temp = buy_2;
            int sold1_temp = sold_1, sold2_temp = sold_2;

            buy_1 = Math.max(buy1_temp, -prices[i]);
            sold_1 = Math.max(sold1_temp, buy1_temp + prices[i]);
            buy_2 = Math.max(buy2_temp, sold1_temp - prices[i]);
            sold_2 = Math.max(sold2_temp, buy2_temp + prices[i]);
        }

        return Math.max(sold_1, sold_2);

    }
    public static void main(String[] args) {
        int[] prices = {3,3,5,0,0,3,1,4};
        int[] prices_1 = {1};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit(prices_1));
    }
}

/**
 *  buy_1  sold_1  buy_2  sold_2
 *
 *
 *
 *  buy_1  sold_1  buy_2  sold_2
 */