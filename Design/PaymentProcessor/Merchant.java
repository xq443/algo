package PaymentProcessor;

public class Merchant {
  public String merchantId;
  public double balance;
  public int timeLimit;

  public Merchant(String merchantId, double balance) {
    this.merchantId = merchantId;
    this.balance = balance;
    this.timeLimit = 0;
  }

  public Merchant(String merchantId, double balance, int timeLimit) {
    this.merchantId = merchantId;
    this.balance = balance;
    this.timeLimit = timeLimit;
  }
}
