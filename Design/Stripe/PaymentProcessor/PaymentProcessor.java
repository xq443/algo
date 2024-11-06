package PaymentProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PaymentProcessor {
  Map<String, Merchant> merchants = new HashMap<>();
  Map<String, PaymentIntent> payments = new HashMap<>();

  public String[] forAllIntentsAndPurposes(String[] commands) {
    for (String command : commands) {
      String action = command.split(" ")[0];

      switch (action) {
      case "INIT":
        String merchantId = command.split(" ")[1];
        double startingBalance = Double.parseDouble(command.split(" ")[2]);
        initMerchants(merchantId, startingBalance);
        break;
      case "CREATE":
        String paymentIntentId = command.split(" ")[1];
        merchantId = command.split(" ")[2];
        double amount = Double.parseDouble(command.split(" ")[3]);
        createPayments(paymentIntentId, merchantId, amount);
        break;
      case "ATTEMPT":
        paymentIntentId = command.split(" ")[1];
        attemptPayment(paymentIntentId);
        break;
      case "SUCCEED":
        paymentIntentId = command.split(" ")[1];
        succeed(paymentIntentId);
        break;
      case "UPDATE":
        paymentIntentId = command.split(" ")[1];
        double newAmount = Double.parseDouble(command.split(" ")[2]);
        update(paymentIntentId, newAmount);
        break;
      case "FAIL":
        paymentIntentId = command.split(" ")[1];
        fail(paymentIntentId);
        break;
      case "REFUND":
        paymentIntentId = command.split(" ")[1];
        refund(paymentIntentId);
        break;
      }
    }
    List<String> ret = new ArrayList<>();
    for (Merchant merchant : merchants.values()) {
      ret.add(merchant.merchantId + " " + merchant.balance);
    }
    return ret.toArray(new String[0]);
  }

  public void initMerchants(String merchantId, double startingBalance) {
    if (!merchants.containsKey(merchantId)) {
      merchants.put(merchantId, new Merchant(merchantId, startingBalance));
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

  public void succeed(String paymentId) {
    PaymentIntent paymentIntent = payments.get(paymentId);
    if (paymentIntent != null &&
        Objects.equals(paymentIntent.state, State.PROCESSING)) {
      paymentIntent.state = State.COMPLETED;
      Merchant merchant = merchants.get(paymentIntent.merchantID);
      if (merchant != null) {
        merchant.balance += paymentIntent.amount;
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

  public void refund(String paymentId) {
    PaymentIntent paymentIntent = payments.get(paymentId);
    if (paymentIntent != null && paymentIntent.state == State.COMPLETED &&
        paymentIntent.isNotRefunded) {
      Merchant merchant = merchants.get(paymentIntent.merchantID);
      if (merchant != null) {
        merchant.balance -= paymentIntent.amount;
        paymentIntent.isNotRefunded = false;
      }
    }
  }

  public static void main(String[] args) {
    PaymentProcessor paymentProcessor1 = new PaymentProcessor();
    PaymentProcessor paymentProcessor2 = new PaymentProcessor();
    PaymentProcessor paymentProcessor3 = new PaymentProcessor();
    String[] commands = {"INIT m1 0",        "INIT m2 10", "CREATE p1 m1 50",
                         "ATTEMPT p1",       "SUCCEED p1", "SUCCEED p1",
                         "CREATE p2 m2 100", "ATTEMPT p2"};
    String[] commands2 = {"INIT m1 0", "CREATE p1 m1 50", "UPDATE p1 100",
                          "ATTEMPT p1", "SUCCEED p1"};
    String[] commands3 = {"INIT m1 0",        "CREATE p1 m1 50", "ATTEMPT p1",
                          "FAIL p1",          "ATTEMPT p1",      "SUCCEED p1",
                          "CREATE p2 m1 100", "ATTEMPT p2",      "SUCCEED p2",
                          "REFUND p2"};
    System.out.println(
        Arrays.toString(paymentProcessor1.forAllIntentsAndPurposes(commands)));
    System.out.println(
        Arrays.toString(paymentProcessor2.forAllIntentsAndPurposes(commands2)));
    System.out.println(
        Arrays.toString(paymentProcessor3.forAllIntentsAndPurposes(commands3)));
  }
}
