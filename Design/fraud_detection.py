import sys

risk_list = []
safe_list = []
mcc_threshold_map = {}
account_mcc_map = {}
min_number_of_transactions = 0
merchant_map = {}
risk_merchants = set()
transaction_account_map = {}


class Merchant:
    def __init__(self, account_id, mcc, threshold):
        self.account_id = account_id
        self.mcc = mcc
        self.threshold = threshold
        self.total_transactions = 0
        self.risk_transactions = 0

    def risk_transaction(self):
        self.total_transactions += 1
        self.risk_transactions += 1
        self.calculate_risk()

    def safe_transaction(self):
        self.total_transactions += 1
        self.calculate_risk()

    def calculate_risk(self):
        if self.total_transactions >= min_number_of_transactions and self.risk_transactions != 0 and self.total_transactions != 0:
            risk = self.risk_transactions / self.total_transactions
            if risk >= self.threshold:
                if self.account_id not in risk_merchants:
                    risk_merchants.add(self.account_id)
            else:
                if self.account_id in risk_merchants:
                    risk_merchants.remove(self.account_id)

    def dispute_transactions(self):
        self.risk_transactions -= 1
        self.calculate_risk()


def main():
    global min_number_of_transactions  # 声明使用全局变量

    safe_list.extend(sys.stdin.readline().strip().replace('"', '').split(','))
    risk_list.extend(sys.stdin.readline().strip().replace('"', '').split(','))

    for line in sys.stdin:
        parts = line.strip().split(',')
        if parts[0] == 'end':
            print(sorted(risk_merchants))
            return
        if len(parts) == 2:
            first, second = parts[0], parts[1]
            if first[:4] == 'acct':
                account_mcc_map[first] = second
            elif first[:7] == 'DISPUTE':
                account_id = transaction_account_map[second]
                merchant_map[account_id].dispute_transactions()
            else:
                mcc_threshold_map[first] = float(second)
        elif len(parts) == 1:
            first = parts[0]
            min_number_of_transactions = int(first)
        else:
            charge, transaction_id, account_id, amount, transaction_type = parts[0], parts[1], parts[2], parts[3], parts[4]
            transaction_account_map[transaction_id] = account_id
            if account_id not in merchant_map:
                mcc = account_mcc_map[account_id]
                threshold = mcc_threshold_map[mcc]
                new_merchant = Merchant(account_id, mcc, threshold)
                merchant_map[account_id] = new_merchant
            if transaction_type in risk_list:
                merchant_map[account_id].risk_transaction()
            if transaction_type in safe_list:
                merchant_map[account_id].safe_transaction()


if __name__ == "__main__":
    main()

# "approved","invalid_pin","expired_card"
# "do_not_honor","stolen_card"," lost_card"
# retail,0.5
# airline,0.25
# restaurant,0.8
# venue,0.25
# acct_1,airline
# acct_2,venue
# acct_3,venue
# 3
# CHARGE,ch_1,acct_1,100,do_not_honor
# CHARGE,ch_2,acct_1,200,approved
# CHARGE,ch_3,acct_1,300,do_not_honor
# CHARGE,ch_4,acct_2,400,approved
# CHARGE,ch_5,acct_2,500,approved
# CHARGE,ch_6,acct_1,600,lost_card
# CHARGE,ch_7,acct_2,700,approved
# CHARGE,ch_8,acct_2,800,approved
# CHARGE,ch_9,acct_3,800,approved
# CHARGE,ch_10,acct_3,700,approved
# CHARGE,ch_11,acct_3,600,approved
# CHARGE,ch_12,acct_3,500,stolen_card
# CHARGE,ch_13,acct_3,500,stolen_card
# CHARGE,ch_14,acct_2,400,stolen_card


# "approved","invalid_pin","expired_card"
# "do_not_honor","stolen_card","lost_card"
# retail,0.8
# venue,0.25
# acct_1,retail
# acct_2,retail
# 2
# CHARGE,ch_1,acct_1,100,do_not_honor
# CHARGE,ch_2,acct_1,200,lost_card
# CHARGE,ch_3,acct_1,300,do_not_honor
# DISPUTE,ch_2
# CHARGE,ch_4,acct_2,400,lost_card
# CHARGE,ch_5,acct_2,500,lost_card
# CHARGE,ch_6,acct_1,600,lost_card
# CHARGE,ch_7,acct_2,700,lost_card
# CHARGE,ch_8,acct_2,800,do_not_honor