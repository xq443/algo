class CardSelection:
    def __init__(self, input_str, supported_cards=None):
        self.input_str = input_str
        # Use an empty set as default if none is provided
        self.supported_cards = supported_cards if supported_cards is not None else set()
        
    def parse_card(self):
        cards = []
        i = 0

        while i < len(self.input_str):
            start = i
            # Find the length of the next card
            while i < len(self.input_str) and self.input_str[i].isdigit():
                i += 1
                
            # Extract the card length; check to avoid IndexError
            if start == i:
                break  # No digits found, break the loop
            
            card_length = int(self.input_str[start:i])

            # Extract the card string based on the extracted length
            card = self.input_str[i:i + card_length]
            i += card_length

            # Append the parsed card to the list
            cards.append(card)

        # Filter the cards to include only the supported ones
        filtered_cards = [card for card in cards if card in self.supported_cards]

        # Sort the filtered cards by their length in ascending order
        filtered_cards.sort(key = len)
        
        # Print supported cards while maintaining order
        for card in filtered_cards:
            print(card)

        return filtered_cards  # Return the filtered and sorted list
    
    def parse_card_generic(self):
        cards = []
        i = 0
        unique_cards = set()
        
        while i < len(self.input_str):
            start = i
            
            while i < len(self.input_str) and self.input_str[i].isdigit():
                i += 1
            
            # card length
            card_length = (int)(self.input_str[start: i])
            
            # card string
            card_string = self.input_str[i: i + card_length]
            i += card_length
            
            if card_string not in unique_cards:
                cards.append(card_string)
                unique_cards.add(card_string)
                
        # Filter the cards to include only the supported ones
        filter_cards = [card for card in cards if card in self.supported_cards]
        
        # Check for generic cards: start with the supported-card prefix
        for card in cards:
            for suppored in self.supported_cards:
                if card.startswith(suppored) and card not in filter_cards:
                    filter_cards.append(card)
                    break  # Stop after adding the first match
                    
        filter_cards.sort(key=len)
        
        return filter_cards
                    
            
            

# Example usage
input_string = "3abc4defg2hm"
supported_cards = {"ab", "hm"}

card_selection = CardSelection(input_string, supported_cards)
parsed_cards = card_selection.parse_card_generic()
print("Parsed Cards:", parsed_cards)
