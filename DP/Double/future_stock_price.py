#!/bin/python3

import fileinput
from typing import List, Tuple


#
# Complete the two functions below.
#
# add_trade() will accept each row of input as a list.
# run() will be called after adding all rows of input and should return an integer representing the total maximum profit.
#
from datetime import datetime
from bisect import bisect_right

class Solution:
    def __init__(self):
        self.tr = []  # List to store the trade information
        
    def add_trade(self, future_trade: List):
        """
        Adds a trade entry to the trade list. The trade consists of stock symbol, 
        trade date, and price.
        """
        stk, dt, pr = future_trade
        # Convert date from string to int format YYYYMMDD
        dt_int = int(datetime.strptime(dt, "%m/%d/%Y").strftime("%Y%m%d"))
        # Convert price to float
        pr = float(pr)
        # Append the trade information as a tuple (stock, date, price)
        self.tr.append((stk, dt_int, pr))
        
    
    def run(self):
        """
        Main method that processes all trades and calculates the maximum profit
        possible by following the rules of buying and selling stock.
        """
        st = {}  # Dictionary to store stock symbol as keys and a list of (date, price) tuples as values
        
        # Group trades by stock symbol and store them
        for stk, dt, pr in self.tr:
            if stk not in st:
                st[stk] = []
            st[stk].append((dt, pr))
            
        possible_trades = []  # To store possible buy/sell pairs for each stock
        
        # Generate all possible valid buy/sell pairs for each stock
        for stk, price_list in st.items():
            price_list.sort()  # Sort by date (ascending)
            n = len(price_list)
            for i in range(n):
                for j in range(i + 1, n):
                    if price_list[j][1] > price_list[i][1]:  # Only consider valid sell after buy
                        buy_date = price_list[i][0]
                        sell_date = price_list[j][0]
                        ratio = price_list[j][1]/ price_list[i][1]  # Profit ratio for this buy/sell pair
                        # Sort possible trades by sell date
                        possible_trades.append((buy_date, sell_date, ratio))
        possible_trades.sort(key=lambda x: x[1])
        
        dp_dates = [datetime.min]  # List of "dp" dates, initialized with the minimum possible date
        dp_amount = [1000.0]  # List of amounts after each transaction, initialized with 1000
        
        for buy_date, sell_date, ratio in possible_trades:
            # Convert buy_date and sell_date to datetime objects for comparison
            buy_date_dt = datetime.strptime(str(buy_date), "%Y%m%d")
            sell_date_dt = datetime.strptime(str(sell_date), "%Y%m%d")
            
            # Use bisect_right to find the position in dp_dates
            idx = bisect_right(dp_dates, buy_date_dt) - 1
            if idx >= 0:
                max_before = dp_amount[idx]
                new_amount = max_before * ratio
                if new_amount > dp_amount[-1]:  # If the new amount is greater, add it
                    dp_dates.append(sell_date_dt)
                    dp_amount.append(new_amount)
                    
        # The maximum amount after all trades
        max_amt = dp_amount[-1]
        
        # Calculate the profit, starting with 1000 and rounding it
        profit = round(max_amt - 1000)
        return profit
  


if __name__ == '__main__':
    solution = Solution()
    for row in fileinput.input():
        future_trade = list(row.strip().replace(" ", "").split(","))
        solution.add_trade(future_trade)
        
    print(solution.run())