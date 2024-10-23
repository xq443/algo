package PaymentProcessor;

public class PaymentIntent {
  public String paymentIntentID;
  public String merchantID;
  public State state;
  public double amount;

  public PaymentIntent(String paymentID, String merchantID, double amount) {
    this.paymentIntentID = paymentID;
    this.merchantID = merchantID;
    this.state = State.REQUIRES_ACTION;
    this.amount = amount;
  }
}
