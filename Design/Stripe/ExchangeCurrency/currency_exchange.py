import json
class ExchangeRate:
    def __init__(self, input, requirement):
        self.input = input
        self.requirement = requirement
        self.ratesMap = {}
        self.parseInput()
        self.parentMap = {} # key is child, value is parent
        
    def parseInput(self):
        
        entries = self.input.split(",")
        for entry in entries:
            parts = entry.split(":")
            if len(parts) == 3:
                currency_from, currency_to, rate = parts
                self.ratesMap[(currency_from, currency_to)] = float(rate)
                self.ratesMap[(currency_to, currency_from)] = 1 / float(rate)
            else:
                raise ValueError("invalid input format")
            

    def exchange(self):
        parts = self.requirement.split(":")
        if len(parts) != 2:
            raise ValueError("invalid requirement format")
         
        currency_from, currency_to = parts
        
        # Check for direct exchange rate
        if(currency_from, currency_to) in self.ratesMap:
            self.parentMap[currency_to] = currency_from  # Track direct parent
            return round(self.ratesMap[(currency_from, currency_to)], 2)
    
        
        # Check for one intermediate currency
        for (currency_from, intermediate) in self.ratesMap:
            if(intermediate, currency_to) in self.ratesMap:
                
                # Store parent relationships in `parentMap`
                self.parentMap[intermediate] = currency_from
                self.parentMap[currency_to] = intermediate
            
                # Get both rates involved in the path
                rate_from_to_intermediate = self.ratesMap[(currency_from, intermediate)]
                rate_intermediate_to_target = self.ratesMap[(intermediate, currency_to)]
                
                total = rate_from_to_intermediate * rate_intermediate_to_target
                
                path = self.printPath(currency_from, currency_to)
                result = {
                    "exchange_path": path,
                    "rate": round(total, 2)
                }
                return json.dumps(result, indent=4)
                
                # return self.printPath(currency_from, currency_to), round(total, 2)
                
            
        raise ValueError(f"Exchange rate from {currency_from} to {currency_to} not found.")
    
    def printPath(self, start, end):
        
        path = []
        curr = end
        
        while curr in self.parentMap:
            path.append(curr)
            curr = self.parentMap[curr] # traverse to the parent node
            
        path.append(start)
        path.reverse()
        
        return "->".join(path)
    
if __name__ == "__main__":
    input = "USD:CAD:1.26,USD:AUD:0.75,USD:JPY:109.23"
    requirement1 = "CAD:USD"
    requirement2 = "CAD:JPY"  # Testing with an indirect conversion
    
    exchangeRate = ExchangeRate(input, requirement2)
    
    try:
        # path, rate = exchangeRate.exchange()
        # print(f"Exchange Path: {path}, Rate: {rate}")
        result_json2 = exchangeRate.exchange()
        print(result_json2)  # JSON formatted output
    except ValueError as e:
        print(e)
    
    