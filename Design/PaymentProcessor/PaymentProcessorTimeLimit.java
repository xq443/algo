package PaymentProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PaymentProcessorTimeLimit {
  Map<String, Merchant> merchants = new HashMap<>();
  Map<String, PaymentIntent> payments = new HashMap<>();

  public String[] forAllIntentsAndPurposes(String[] commands) {
    for (String command : commands) {
      String[] response = command.split(" ");
      int timestamp = Integer.parseInt(response[0]);
      String action = response[1];

      switch (action) {
      case "INIT":
        String merchantId = command.split(" ")[2];
        double startingBalance = Double.parseDouble(command.split(" ")[3]);
        int timeLimit = response.length > 4 ? Integer.parseInt(response[4]) : 0;
        initMerchants(merchantId, startingBalance, timeLimit);
        break;
      case "CREATE":
        String paymentIntentId = command.split(" ")[2];
        merchantId = command.split(" ")[3];
        double amount = Double.parseDouble(command.split(" ")[4]);
        createPayments(paymentIntentId, merchantId, amount);
        break;
      case "ATTEMPT":
        paymentIntentId = command.split(" ")[2];
        attemptPayment(paymentIntentId);
        break;
      case "SUCCEED":
        paymentIntentId = command.split(" ")[2];
        succeed(paymentIntentId, timestamp);
        break;
      case "UPDATE":
        paymentIntentId = command.split(" ")[2];
        double newAmount = Double.parseDouble(command.split(" ")[3]);
        update(paymentIntentId, newAmount);
        break;
      case "FAIL":
        paymentIntentId = command.split(" ")[2];
        fail(paymentIntentId);
        break;
      case "REFUND":
        paymentIntentId = command.split(" ")[2];
        refund(paymentIntentId, timestamp);
        break;
      }
    }
    List<String> ret = new ArrayList<>();
    for (Merchant merchant : merchants.values()) {
      ret.add(merchant.merchantId + " " + merchant.balance);
    }
    return ret.toArray(new String[0]);
  }

  public void initMerchants(String merchantId, double startingBalance,
                            int timeLimit) {
    if (!merchants.containsKey(merchantId)) {
      merchants.put(merchantId,
                    new Merchant(merchantId, startingBalance, timeLimit));
    }
  }

  public void createPayments(String paymentId, String merchantId,
                             double startingBalance) {
    if (merchants.containsKey(merchantId) && startingBalance >= 0 &&
        !payments.containsKey(paymentId)) {
      payments.put(paymentId,
                   new PaymentIntent(paymentId, merchantId, startingBalance));
    }
  }

  public void attemptPayment(String paymentId) {
    PaymentIntent paymentIntent = payments.get(paymentId);
    if (paymentIntent != null && paymentIntent.state == State.REQUIRES_ACTION) {
      paymentIntent.state = State.PROCESSING;
    }
  }

  public void succeed(String paymentId, int succeedTimestamp) {
    PaymentIntent paymentIntent = payments.get(paymentId);
    if (paymentIntent != null &&
        Objects.equals(paymentIntent.state, State.PROCESSING)) {
      paymentIntent.state = State.COMPLETED;
      Merchant merchant = merchants.get(paymentIntent.merchantID);
      if (merchant != null) {
        merchant.balance += paymentIntent.amount;
        paymentIntent.succeedTimestamp = succeedTimestamp;
      }
    }
  }

  public void update(String paymentId, double newAmount) {
    PaymentIntent paymentIntent = payments.get(paymentId);
    if (paymentIntent != null && paymentIntent.state == State.REQUIRES_ACTION &&
        newAmount >= 0) {
      paymentIntent.amount = newAmount;
    }
  }

  public void fail(String paymentId) {
    PaymentIntent paymentIntent = payments.get(paymentId);
    if (paymentIntent != null && paymentIntent.state == State.PROCESSING) {
      paymentIntent.state = State.REQUIRES_ACTION;
    }
  }

  public void refund(String paymentId, int timestamp) {
    PaymentIntent paymentIntent = payments.get(paymentId);
    if (paymentIntent != null && paymentIntent.state == State.COMPLETED &&
        paymentIntent.isNotRefunded) {
      Merchant merchant = merchants.get(paymentIntent.merchantID);
      if (merchant != null) {
        if (merchant.timeLimit == 0 ||
            (timestamp - paymentIntent.succeedTimestamp <=
             merchant.timeLimit)) {
          merchant.balance -= paymentIntent.amount;
          paymentIntent.isNotRefunded = false;
        }
      }
    }
  }

  public static void main(String[] args) {
    PaymentProcessorTimeLimit paymentProcessor =
        new PaymentProcessorTimeLimit();
    String[] commands = {
        "1 INIT m1 0 2", "2 CREATE p1 m1 100", "3 CREATE p2 m1 50",
        "4 ATTEMPT p1",  "5 ATTEMPT p2",       "8 SUCCEED p1",
        "10 SUCCEED p2", "11 REFUND p1",       "16 REFUND p2"};
    System.out.println(
        Arrays.toString(paymentProcessor.forAllIntentsAndPurposes(commands)));
  }
}
