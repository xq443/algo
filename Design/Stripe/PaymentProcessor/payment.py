import math
import sys
from enum import Enum


merchant_map = {}
payment_intent_map = {}


class Merchant:
    def __init__(self, timestamp, merchant_id, starting_balance, refund_timeout_limit):
        self.timestamp = timestamp
        self.merchant_id = merchant_id
        self.starting_balance = starting_balance
        self.refund_timeout_limit = refund_timeout_limit

    def incr_balance(self, amount):
        self.starting_balance += amount

    def decr_balance(self, amount, complete_timestamp, req_refund_timestamp):
        if complete_timestamp + self.refund_timeout_limit >= req_refund_timestamp:
            self.starting_balance -= amount
            return True
        return False

    def get_balance(self):
        return self.starting_balance


class PaymentIntentStatus(Enum):
    REQUIRES_ACTION = 1
    PROCESSING = 2
    COMPLETED = 3
    REFUND = 4


class PaymentIntent:
    def __init__(self, timestamp, payment_intent_id, merchant_id, amount):
        self.timestamp = timestamp
        self.payment_intent_id = payment_intent_id
        self.merchant_id = merchant_id
        self.amount = amount
        self.status = PaymentIntentStatus.REQUIRES_ACTION

    def status_transfer(self, action, req_timestamp):
        if action == 'ATTEMPT' and self.status == PaymentIntentStatus.REQUIRES_ACTION:
            self.status = PaymentIntentStatus.PROCESSING
            self.timestamp = req_timestamp
        elif action == 'SUCCEED' and self.status == PaymentIntentStatus.PROCESSING:
            self.status = PaymentIntentStatus.COMPLETED
            merchant_map[self.merchant_id].incr_balance(self.amount)
            self.timestamp = req_timestamp
        elif action == 'FAIL' and self.status == PaymentIntentStatus.PROCESSING:
            self.status = PaymentIntentStatus.REQUIRES_ACTION
            self.timestamp = req_timestamp
        elif action == 'REFUND' and self.status == PaymentIntentStatus.COMPLETED:
            if merchant_map[self.merchant_id].decr_balance(self.amount, self.timestamp, req_timestamp):
                self.status = PaymentIntentStatus.REFUND
                self.timestamp = req_timestamp

    def update_amount(self, new_amount, req_timestamp):
        if self.status == PaymentIntentStatus.REQUIRES_ACTION:
            self.amount = new_amount
            self.timestamp = req_timestamp


def parse_and_execute_command(timestamp, command_parts):
    command = command_parts[0]
    args = command_parts[1:]

    if command == 'INIT':
        merchant_id, balance = args[0], int(args[1])
        if len(args) == 2 or int(args[2]) < 0:
            refund_timeout = math.inf
        else:
            refund_timeout = int(args[2])
        if merchant_id in merchant_map:
            print(f"Error: Merchant {merchant_id} already exists")
            return
        merchant = Merchant(timestamp, merchant_id, balance, refund_timeout)
        merchant_map[merchant_id] = merchant

    elif command == 'CREATE':
        payment_id, merchant_id, amount = args[0], args[1], int(args[2])
        if payment_id in payment_intent_map:
            print(f"Error: Payment Intent {payment_id} already exists")
            return
        if merchant_id not in merchant_map:
            print(f"Error: Merchant {merchant_id} not found")
            return
        if amount < 0:
            print(f"Error: Amount {amount} is negative")
            return
        payment = PaymentIntent(timestamp, payment_id, merchant_id, amount)
        payment_intent_map[payment_id] = payment

    elif command == 'UPDATE':
        payment_id, amount = args[0], int(args[1])
        if payment_id not in payment_intent_map:
            print(f"Error: Payment Intent {payment_id} not found")
            return
        if amount < 0:
            print(f"Error: Amount {amount} is negative")
            return
        payment_intent_map[payment_id].update_amount(amount, timestamp)

    elif command in ['ATTEMPT', 'SUCCEED', 'FAIL', 'REFUND']:
        payment_id = args[0]
        if payment_id not in payment_intent_map:
            print(f"Error: Payment Intent {payment_id} not found")
            return
        payment_intent_map[payment_id].status_transfer(command, timestamp)

    else:
        print(f"Error: Unknown command {command}")


def main():
    for line in sys.stdin:
        parts = line.strip().split()
        if len(parts) == 1:
            # print result
            for merchant_id in merchant_map:
                print(merchant_id, merchant_map[merchant_id].get_balance())
            return
        timestamp = int(parts[0])
        command_parts = parts[1:]
        parse_and_execute_command(timestamp, command_parts)


if __name__ == "__main__":
    main()
