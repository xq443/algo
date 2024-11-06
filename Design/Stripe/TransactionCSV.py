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
cust1,merchantA,2022-01-07,Visa,500
cust2,merchantA,2022-01-07,Visa,250
cust3,merchantB,2022-01-08,MasterCard,1250
cust4,merchantC,2022-01-09,Visa,1500
"""
contracts_csv2 = """contract_id,merchant_id,payout_date,card_type,amount
contract1,merchantA,2022-01-07,Visa,750
contract2,merchantC,2022-01-09,Visa,1300
contract3,merchantD,2022-01-09,Visa,1200

"""


class TransactionCSV:
    def __init__(self, input_csv, contract_csv = None):
        self.input_csv = input_csv
        self.contract_csv = contract_csv
        
    def generate_receivables(self):
        receivables = defaultdict(int)
        
        # Parse the CSV file
        csv_reader = csv.DictReader(StringIO(self.input_csv))

        for row in csv_reader:
            merchant_id = row['merchant_id']
            card_type = row['card_type']
            amount = int(row['amount'])
            payout_date = row['payout_date']

            key = (merchant_id, card_type, payout_date)
            receivables[key] += amount
        return receivables
        

    def register_receivables(self):
        receivables = self.generate_receivables()
            
        # Output
        output_lines = ["merchant_id,card_type,payout_date,amount"]
        for (merchant_id, card_type, payout_date), amount in receivables.items():
            output_lines.append(f"{merchant_id},{card_type},{payout_date},{amount}")
            
        return "\n".join(output_lines)

    
    def update_register_receivables(self):
        
        receivables = self.generate_receivables()
        
        # Parse CSV file for contracts
        contract_reader = csv.DictReader(StringIO(self.contract_csv))

        for row in contract_reader:
            merchant_id = row['merchant_id']
            card_type = row['card_type']
            amount = int(row['amount'])
            payout_date = row['payout_date']
            contract_id = row['contract_id']
            
            # Key for contracts
            contract_key = (merchant_id, card_type, payout_date)

            # Remove merchant receivable if it exists
            if contract_key in receivables:
                del receivables[contract_key]
                receivables[(contract_id, card_type, payout_date)] = amount
            else:
                receivables[(contract_id, card_type, payout_date)] += amount
            
        # Prepare output
        ret = ["id,card_type,payout_date,amount"]

        for (id, card_type, payout_date), amount in receivables.items():
            ret.append(f"{id},{card_type},{payout_date},{amount}")
        
        return "\n".join(ret)
    
    def update_receivables_deduction(self):
        
        receivables = self.generate_receivables()
        
        # Parse CSV file for contracts
        contract_reader = csv.DictReader(StringIO(self.contract_csv))

        for row in contract_reader:
            merchant_id = row['merchant_id']
            card_type = row['card_type']
            amount = int(row['amount'])
            payout_date = row['payout_date']
            contract_id = row['contract_id']
            
            # Key for contracts
            contract_key = (merchant_id, card_type, payout_date)
            
            if contract_key in receivables:
                receivables[contract_key] -= amount if receivables[contract_key] > amount else receivables[contract_key]
       
            receivables[(contract_id, card_type, payout_date)] += amount
                
            ret = ["id,card_type,payout_date,amount"]
            for (id, card_type,payout_date), amount in receivables.items():
                ret.append(f"{id},{card_type},{payout_date},{amount}")
        
        return "\n".join(ret)
    

        
                

        
        
    
    
    
    # def write_to_csv(self, output_file, receivables):
    #     with open(output_file, mode='w', newline='') as file:
    #         writer = csv.writer(file)
    #         writer.writerow(["id,card_type,payout_date,amount"])
            
    #         for (id, card_type, payout_date), amount in receivables.items():
    #             writer.writerow([id, card_type, payout_date, amount])
            



transaction1 = TransactionCSV(input_csv1)
print(transaction1.register_receivables(), "\n")
print(transaction1.update_register_receivables())


transaction2 = TransactionCSV(input_csv2, contracts_csv2)
print(transaction2.register_receivables(), "\n")
print(transaction2.update_register_receivables(), "\n")
print(transaction2.update_receivables_deduction())
