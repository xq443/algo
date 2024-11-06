import json

class ShippingCaculator:
    def __init__(self, order, shipping_cost):
        self.order = order
        self.shipping_cost = shipping_cost
    
    def calculate_shipping_cost(self):
        country = self.order["country"]
        products = self.order["products"]
        
        total_cost = 0
        
        # search our target json by using the country index
        country_shipping_cost = self.shipping_cost.get(country)
        if country_shipping_cost is None:
            raise ValueError(f"Shipping cost not found for country {country}")
        
        
        cost_mapping = {}
        # extract the product cost and build up the dic
        for item in country_shipping_cost:
            product = item["product"]
            cost = item["cost"]
            cost_mapping[product] = cost
        
        # calculate the total cost by using the cost_mappning
        for item in products:
            product = item["product"]
            quantity = item["quantity"]
            
            if product in cost_mapping:
                total_cost += cost_mapping[product] * quantity
            else:
                raise ValueError(f"Shipping cost not found for product {product}")
            
        return total_cost
            
        
if __name__ == "__main__":
    order = {
        "country": "CA",
        "products": [
            {"product": "mouse", "quantity": 20},
            {"product": "laptop", "quantity": 5}
        ]
    }
    
    shipping_cost = {
        "US": [
            {"product": "mouse", "cost": 550},
            {"product": "laptop", "cost": 1000}
        ],
        
        "CA": [
            {"product": "mouse", "cost": 750},
            {"product": "laptop", "cost": 1100}
        ]
    }
    
    # Create a ShippingCalculator instance
    shipping_calculator = ShippingCaculator(order, shipping_cost)
    
    # Calculate the total shipping cost
    try:
        total_shipping_cost = shipping_calculator.calculate_shipping_cost()
        print(f"Total shipping cost: ${total_shipping_cost}")
    except ValueError as e:
        print(e)