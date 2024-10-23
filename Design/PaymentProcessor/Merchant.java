package PaymentProcessor;

public class Merchant {
  public String merchantId;
  public double balance;

  public Merchant(String merchantId, double balance) {
    this.merchantId = merchantId;
    this.balance = balance;
  }
}
