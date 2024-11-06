import json
class ExchangeRate:
    def __init__(self, input, requirement):
        self.input = input
        self.requirement = requirement
        self.ratesMap = {}
        self.parseInput()
        self.paths = []  # Store all feasible paths

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
        
        visited = set()
        self.dfs(currency_from, currency_to, 1.0, visited, [])
        
        if not self.paths:  # Check if no paths were found
            raise ValueError(f"No valid exchange path found from {currency_from} to {currency_to}")
        
        # Prepare the final result in JSON format
        result = {
            "exchange_paths": [{
                "path": "->".join(path),
                "rate": rate
            } for path, rate in self.paths]
        }
        
        return json.dumps(result, indent=4)
        
        # return self.paths  # Return all found paths
        
    def dfs(self, current_currency, target_currency, current_rate, visited, path):
        if current_currency in visited:
            return
        
        # Mark the current currency as visited
        visited.add(current_currency)
        path.append(current_currency)
        
        # If we reached the target currency, save the path and rate
        if current_currency == target_currency:
            self.paths.append((list(path), round(current_rate, 2)))
        else:
            for (from_currency, to_currency) in self.ratesMap:
                if from_currency == current_currency:  # Only explore outgoing edges
                    # Calculate new rate and continue DFS
                    new_rate = current_rate * self.ratesMap[(from_currency, to_currency)]
                    self.dfs(to_currency, target_currency, new_rate, visited, path)
        
        # Backtrack: unmark the current currency and remove it from the path
        visited.remove(current_currency)
        path.pop()

if __name__ == "__main__":
    input = "USD:CAD:1.26,USD:AUD:0.75,USD:JPY:109.23,CAD:JPY:33.2"
    requirement1 = "CAD:USD"
    requirement2 = "CAD:JPY"  # Testing with an indirect conversion
    
    exchangeRate = ExchangeRate(input, requirement2)
    
    try:
        paths = exchangeRate.exchange()
        # for path, rate in paths:
        #     print(f"Exchange Path: {' -> '.join(path)}, Rate: ${rate}")
        print(paths)
    except ValueError as e:
        print(e)
