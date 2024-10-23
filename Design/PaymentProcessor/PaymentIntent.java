package PaymentProcessor;

public class PaymentIntent {
  public String paymentIntentID;
  public String merchantID;
  public State state;
  public double amount;
  public boolean isNotRefunded;
  public int succeedTimestamp;

  public PaymentIntent(String paymentID, String merchantID, double amount, int succeedTimestamp) {
    this.paymentIntentID = paymentID;
    this.merchantID = merchantID;
    this.state = State.REQUIRES_ACTION;
    this.amount = amount;
    this.isNotRefunded = true;
    this.succeedTimestamp = succeedTimestamp;
  }

  public PaymentIntent(String paymentIntentID, String merchantID, double amount) {
    this.paymentIntentID = paymentIntentID;
    this.merchantID = merchantID;
    this.state = State.REQUIRES_ACTION;
    this.isNotRefunded = true;
    this.amount = amount;
  }
}
