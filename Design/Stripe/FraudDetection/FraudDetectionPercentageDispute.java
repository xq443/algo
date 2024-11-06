package FraudDetection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FraudDetectionPercentageDispute {
  public String detectFraudulentMerchants(
      String nonFraudCodes,
      String fraudCodes,
      List<String> mccThresholds,
      List<String> accountIdMcc,
      String min,
      List<String> charges) {

    Set<String> fraudCodeSet = new HashSet<>(Arrays.asList(fraudCodes.split(",")));

    Map<String, Double> mccToThresholds = new HashMap<>();
    for(String mccToThreshold : mccThresholds){
      String mcc = mccToThreshold.split(",")[0];
      double threshold = Double.parseDouble(mccToThreshold.split(",")[1]);
      mccToThresholds.put(mcc, threshold);
    }

    Map<String, String> accountIdToMcc = new HashMap<>();
    for(String accountId : accountIdMcc){
      String account = accountId.split(",")[0];
      String mcc = accountId.split(",")[1];
      accountIdToMcc.put(account, mcc);
    }

    Map<String, Integer> accountToTotalCharges = new HashMap<>();
    Map<String, Integer> accountToFraud = new HashMap<>();
    Set<String> disputeChargeId = new HashSet<>();
    Map<String, String> chargeIdToAccountId = new HashMap<>();

    for(String transaction : charges){

      if(transaction.split(",")[0].equals("CHARGE")) {
        String acc = transaction.split(",")[2];
        String code = transaction.split(",")[4];
        String chargeId = transaction.split(",")[1];

        accountToTotalCharges.put(acc, accountToTotalCharges.getOrDefault(acc, 0) + 1);

        if (fraudCodeSet.contains(code)) {
          accountToFraud.put(acc, accountToFraud.getOrDefault(acc, 0) + 1);
          chargeIdToAccountId.put(chargeId, acc);
        }
      } else if(transaction.split(",")[0].equals("DISPUTE")) {
        String chargeId = transaction.split(",")[1];
        // If this charge was previously tracked, handle dispute
        if(chargeIdToAccountId.containsKey(chargeId)){
          disputeChargeId.add(chargeId);
        }
      }
    }

    // Adjust fraud counts for disputed transactions
    for(String chargeId : disputeChargeId){
      String acc = chargeIdToAccountId.get(chargeId);
      if(accountToFraud.containsKey(acc)){
        accountToFraud.put(acc, accountToFraud.get(acc) - 1);
        if(accountToFraud.get(acc) == 0){
          accountToFraud.remove(acc);
        }
      }
    }

    List<String> ret = new ArrayList<>();
    for(String acc : accountToTotalCharges.keySet()){
      int total = accountToTotalCharges.get(acc);
      if(total >= Integer.parseInt(min)) {
        int fraud = accountToFraud.get(acc);
        String mcc = accountIdToMcc.get(acc);
        double percentage = (double) fraud / total;
        double threshold = mccToThresholds.getOrDefault(mcc, 0.0);
        if(percentage >= threshold){
          ret.add(acc);
        }
      }
    }
    Collections.sort(ret);
    return String.join(",", ret);
  }

  public static void main(String[] args) {
    String nonFraudCodes = "approved,invalid_pin,expired_card"; // Single string with comma-separated values
    String fraudCodes = "do_not_honor,stolen_card,lost_card";   // Single string with comma-separated values
    List<String> mccThresholds = Arrays.asList("retail,0.8", "airline,0.6", "venue,0.5", "restaurant,0.8");
    List<String> merchantMCCs = Arrays.asList("acct_1,airline", "acct_2,venue", "acct_3,venue");
    String minTransactions = "2";
    List<String> transactions = Arrays.asList(
        "CHARGE,ch_1,acct_1,100,do_not_honor",
        "CHARGE,ch_2,acct_1,200,approved",
        "CHARGE,ch_3,acct_1,300,do_not_honor",
        "DISPUTE,ch_2",
        "CHARGE,ch_4,acct_2,100,approved",
        "CHARGE,ch_5,acct_2,200,lost_card",
        "CHARGE,ch_6,acct_2,300,lost_card",
        "CHARGE,ch_7,acct_3,100,lost_card",
        "CHARGE,ch_8,acct_2,200,stolen_card",
        "CHARGE,ch_9,acct_3,100,approved");

    FraudDetectionPercentageDispute f = new FraudDetectionPercentageDispute();
    String fraudulentMerchants = String.valueOf(f.detectFraudulentMerchants(
        nonFraudCodes, fraudCodes, mccThresholds, merchantMCCs, minTransactions, transactions
    ));
    System.out.println(fraudulentMerchants);
  }
}
