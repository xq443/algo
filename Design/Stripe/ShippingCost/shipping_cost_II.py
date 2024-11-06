class ShippingCalculator:
    def __init__(self, order, shipping_cost):
        self.order = order
        self.shipping_cost = shipping_cost
    
    def calculate_shipping_cost(self):
        country = self.order["country"]
        products = self.order["products"]
        
        total_cost = 0
        
        country_shipping_cost = self.shipping_cost.get(country)
        if country_shipping_cost is None:
            raise ValueError(f"Shipping cost not found for country {country}")
    
        for item in products:
            product = item["product"]
            quantity = item["quantity"]
        
            application_cost = self.get_applicable_cost(country_shipping_cost, product, quantity)
            total_cost += application_cost

        return total_cost
    
    def get_applicable_cost(self, country_shipping_cost, product, quantity):
        total = 0
        for cost_info in country_shipping_cost:
            if cost_info["product"] == product:
                for tier in cost_info["costs"]:
                    min_quantity = tier["minQuantity"]
                    max_quantity = tier["maxQuantity"]
            
                    if quantity >= min_quantity and (max_quantity is None or quantity <= max_quantity):
                        total += tier["cost"] * quantity
                        return total
                    elif quantity > max_quantity:
                        total += tier["cost"] * max_quantity
                        quantity -= max_quantity  
                    
        if quantity > 0:
            raise ValueError(f"No applicable cost tier for quantity: {quantity}")


if __name__ == "__main__":
    order_us = {
        "country": "US",
        "products": [
            {"product": "mouse", "quantity": 20},
            {"product": "laptop", "quantity": 5}
        ]
    }

    order_ca = {
        "country": "CA",
        "products": [
            {"product": "mouse", "quantity": 20},
            {"product": "laptop", "quantity": 5}
        ]
    }

    shipping_cost = {
        "US": [
            {
                "product": "mouse",
                "costs": [
                    {
                        "minQuantity": 0,
                        "maxQuantity": None,
                        "cost": 550
                    }
                ]
            },
            {
                "product": "laptop",
                "costs": [
                    {
                        "minQuantity": 0,
                        "maxQuantity": 2,
                        "cost": 1000
                    },
                    {
                        "minQuantity": 3,
                        "maxQuantity": None,
                        "cost": 900
                    }
                ]
            }
        ],
        "CA": [
            {
                "product": "mouse",
                "costs": [
                    {
                        "minQuantity": 0,
                        "maxQuantity": None,
                        "cost": 750
                    }
                ]
            },
            {
                "product": "laptop",
                "costs": [
                    {
                        "minQuantity": 0,
                        "maxQuantity": 2,
                        "cost": 1100
                    },
                    {
                        "minQuantity": 3,
                        "maxQuantity": None,
                        "cost": 1000
                    }
                ]
            }
        ]
    }

    # Create a ShippingCalculator instance for US order
    shipping_calculator_us = ShippingCalculator(order_us, shipping_cost)

    try:
        total_shipping_cost_us = shipping_calculator_us.calculate_shipping_cost()
        print(f"Total shipping cost for US order: ${total_shipping_cost_us}")
    except ValueError as e:
        print(e)

    # Create a ShippingCalculator instance for CA order
    shipping_calculator_ca = ShippingCalculator(order_ca, shipping_cost)

    try:
        total_shipping_cost_ca = shipping_calculator_ca.calculate_shipping_cost()
        print(f"Total shipping cost for CA order: ${total_shipping_cost_ca}")
    except ValueError as e:
        print(e)
