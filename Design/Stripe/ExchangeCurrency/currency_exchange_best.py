import heapq
import json

class ExchangeRate:
    def __init__(self, input, requirement):
        self.input = input
        self.requirement = requirement
        self.ratesMap = {}
        self.parseInput()
        
    def parseInput(self):
        entries = self.input.split(",")
        for entry in entries:
            parts = entry.split(":")
            if len(parts) == 3:
                currency_from, currency_to, rate = parts
                self.ratesMap[(currency_from, currency_to)] = float(rate)
                self.ratesMap[(currency_to, currency_from)] = 1 / float(rate)
            else:
                raise ValueError("Invalid input format")
    
    def exchange(self):
        parts = self.requirement.split(":")
        if len(parts) != 2:
            raise ValueError("Invalid requirement format")
        
        currency_from, currency_to = parts
        
        # Priority queue to store (negative_rate, current_currency)
        max_heap = []
        heapq.heappush(max_heap, (-1.0, currency_from))
        
        # Dictionary to track the best rate to each currency
        best_rates = {currency_from: 1.0}
        self.parentMap = {}  # To track the path
        
        while max_heap:
            current_rate, current_currency = heapq.heappop(max_heap)
            current_rate = -current_rate
            
            # If we reached the target currency, construct the path
            if current_currency == currency_to:
                
                # return self.buildPath(currency_from, currency_to), round(current_rate, 2)
                # Create the result in JSON format
                result = {
                    "path": self.buildPath(currency_from, currency_to),
                    "rate": round(current_rate, 2)
                }
                return json.dumps(result)  # Return as JSON string
            
            # Explore neighbors
            for (from_currency, to_currency) in self.ratesMap:
                if from_currency == current_currency:
                    new_rate = current_rate * self.ratesMap[(from_currency, to_currency)]
                    
                    # Only consider this new rate if it's better
                    if to_currency not in best_rates or new_rate > best_rates[to_currency]:
                        best_rates[to_currency] = new_rate
                        self.parentMap[to_currency] = current_currency  # Track the path
                        heapq.heappush(max_heap, (-new_rate, to_currency))  # Push negative rate for max-heap
        
        raise ValueError(f"Exchange rate from {currency_from} to {currency_to} not found.")
    
    def buildPath(self, start, end):
        path = []
        curr = end
        
        while curr in self.parentMap:
            path.append(curr)
            curr = self.parentMap[curr]  # Traverse to the parent node
        
        path.append(start)
        path.reverse()
        
        return "->".join(path)

if __name__ == "__main__":
    input = "USD:CAD:1.26,USD:AUD:0.75,USD:JPY:109.23,CAD:JPY:10000"
    requirement1 = "CAD:USD"
    requirement2 = "CAD:JPY"  # Testing with an indirect conversion
    
    exchangeRate = ExchangeRate(input, requirement1)  # Change requirement to test different paths
    
    try:
        result1 = exchangeRate.exchange()
        print(f"Exchange Path: {result1}")
    except ValueError as e:
        print(e)

    # Testing another requirement
    exchangeRate2 = ExchangeRate(input, requirement2)
    
    try:
        result2 = exchangeRate2.exchange()
        # path, rate = exchangeRate2.exchange()
        print(f"Exchange Path: {result2}")
    except ValueError as e:
        print(e)

# Time Complexity: O(V^2 log V), but building a heap O(n)
# Space Complexity: O(V^2)