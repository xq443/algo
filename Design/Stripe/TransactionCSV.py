import csv
from collections import defaultdict 
from io import StringIO


# Example usage
input_csv1 = """customer_id,merchant_id,payout_date,card_type,amount
cust1,merchantA,2021-12-30,Visa,150
cust2,merchantA,2021-12-30,Visa,200
cust3,merchantB,2021-12-31,MasterCard,300
cust4,merchantA,2021-12-30,Visa,50"""
contracts_csv1 = """contract_id,merchant_id,payout_date,card_type,amount
contract1,merchantA,2022-01-05,Visa,500
"""

input_csv2 = """customer_id,merchant_id,payout_date,card_type,amount
cust1,merchantA,2022-01-05,Visa,300
cust2,merchantA,2022-01-05,Visa,200
cust3,merchantB,2022-01-06,MasterCard,1000"""

constracts_csv2 = """contract_id,merchant_id,payout_date,card_type,amount
contract1,merchantA,2022-01-05,Visa,500
"""

input_csv3 = """customer_id,merchant_id,payout_date,card_type,amount
cust1,merchantA,2022-01-07,Visa,500
cust2,merchantA,2022-01-07,Visa,250
cust3,merchantB,2022-01-08,MasterCard,1250
cust4,merchantC,2022-01-09,Visa,1500
"""
contracts_csv3 = """contract_id,merchant_id,payout_date,card_type,amount
contract1,merchantA,2022-01-07,Visa,750
contract2,merchantC,2022-01-09,Visa,1300
contract3,merchantD,2022-01-09,Visa,1200
"""

class TransactionCSV:
    def __init__(self, input_csv, contract_csv = None):
        self.input_csv = input_csv
        self.contract_csv = contract_csv
        self.receivables = self.generate_receivables()
        
    def generate_receivables(self):
        # Parse the CSV file
        csv_reader = csv.DictReader(StringIO(self.input_csv))
        
        # Initialize a defaultdict to store summed amounts by (merchant_id, card_type, payout_date)
        receivables = defaultdict(int)
    
        for row in csv_reader:
            merchant_id = row['merchant_id']
            card_type = row['card_type']
            amount = int(row['amount']) if row['amount'] else 0
            payout_date = row['payout_date']

            key = (merchant_id, card_type, payout_date)
            receivables[key] += amount
        return receivables
        

    def register_receivables(self):
            
        # Output
        output_lines = ["merchant_id,card_type,payout_date,amount"]
        for (merchant_id, card_type, payout_date), amount in self.receivables.items():
            output_lines.append(f"{merchant_id},{card_type},{payout_date},{amount}")
            
        return "\n".join(output_lines)

    
    def update_receivables(self):
        
        # Parse CSV file for contracts
        contract_reader = csv.DictReader(StringIO(self.contract_csv))

        for row in contract_reader:
            merchant_id = row['merchant_id']
            card_type = row['card_type']
            amount = int(row['amount']) if row['amount'] else 0
            payout_date = row['payout_date']
            contract_id = row['contract_id']
            
            # Key for contracts
            contract_key = (merchant_id, card_type, payout_date)

            # Remove merchant receivable if it exists
            if contract_key in self.receivables and amount == self.receivables[contract_key]:
                del self.receivables[contract_key]
                self.receivables[(contract_id, card_type, payout_date)] = amount
                
            
        # Prepare output
        ret = ["id,card_type,payout_date,amount"]

        for (id, card_type, payout_date), amount in self.receivables.items():
            ret.append(f"{id},{card_type},{payout_date},{amount}")
        
        return "\n".join(ret)
    
    def update_receivables_deduction(self):
    
        # Parse CSV file for contracts
        contract_reader = csv.DictReader(StringIO(self.contract_csv))

        for row in contract_reader:
            merchant_id = row['merchant_id']
            card_type = row['card_type']
            amount = int(row['amount'])
            payout_date = row['payout_date']
            contract_id = row['contract_id']
            
            # Key for contracts
            key = (merchant_id, card_type, payout_date)
            
            if key in self.receivables:
                self.receivables[key] -= amount if self.receivables[key] > amount else self.receivables[key]
       
            self.receivables[(contract_id, card_type, payout_date)] += amount
                
            ret = ["id,card_type,payout_date,amount"]
            for (id, card_type,payout_date), amount in self.receivables.items():
                ret.append(f"{id},{card_type},{payout_date},{amount}")
        
        return "\n".join(ret)
    
    
    # def write_to_csv(self, output_file, receivables):
    #     with open(output_file, mode='w', newline='') as file:
    #         writer = csv.writer(file)
    #         writer.writerow(["id,card_type,payout_date,amount"])
            
    #         for (id, card_type, payout_date), amount in receivables.items():
    #             writer.writerow([id, card_type, payout_date, amount])
            

if __name__ == "__main__":
    

    transaction1 = TransactionCSV(input_csv1)
    print(transaction1.register_receivables(), "\n")

    transaction2 = TransactionCSV(input_csv2, constracts_csv2)
    print(transaction2.update_receivables(), "\n")
    
    transaction3 = TransactionCSV(input_csv3, contracts_csv3)
    print(transaction3.update_receivables_deduction())
    
