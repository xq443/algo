package Basic;

public class BestTimeBuyAndSellStockIII {
    public int maxProfit(int[] prices){
        // J - 1  SELL1 BUY1 SELL2  BUY2
        // J      BUY1  SELL1 SELL2 BUY2
        if(prices.length == 0 || prices.length == 1) return 0;
        int prevBuy_1 = Integer.MIN_VALUE, prevBuy_2 = Integer.MIN_VALUE;
        int prevSell_1 = 0, prevSell_2 = 0;

        for (int price : prices) {
            int currBuy_1 = Math.max(prevBuy_1, -price);
            int currSell_1 = Math.max(prevSell_1, prevBuy_1 + price);
            int currBuy_2 = Math.max(prevBuy_2, prevSell_1 - price);
            int currSell_2 = Math.max(prevSell_2, currBuy_2 + price);

            prevBuy_2 = currBuy_2;
            prevBuy_1 = currBuy_1;
            prevSell_1 = currSell_1;
            prevSell_2 = currSell_2;
        }
        return Math.max(prevSell_1, prevSell_2);
    }
    public static void main(String[] args) {
        BestTimeBuyAndSellStockIII bestTimeBuyAndSellStockIII = new BestTimeBuyAndSellStockIII();
        int[] prices = {3,3,5,0,0,3,1,4};
        int[] prices_1 = {1,2,3,4,5};
        System.out.println(bestTimeBuyAndSellStockIII.maxProfit(prices));
        System.out.println(bestTimeBuyAndSellStockIII.maxProfit(prices_1));
    }
}