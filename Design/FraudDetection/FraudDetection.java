package FraudDetection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FraudDetection {
  public List<String> detectFraudulentMerchants(
      String[] nonFraudCodes,
      String[] fraudCodes,
      String[] mccThresholds,
      String[] accountIdMcc,
      String min,
      String[] charges){

    //Set<String> nonFraudCodeSet = new HashSet<>(Arrays.asList(nonFraudCodes));
    Set<String> fraudCodeSet = new HashSet<>(Arrays.asList(fraudCodes));

    Map<String, Integer> mccToThresholds = new HashMap<>();
    for(String mccToThreshold : mccThresholds){
      String mcc = mccToThreshold.split(",")[0];
      int threshold = Integer.parseInt(mccToThreshold.split(",")[1]);
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
    for(String transaction : charges){

      if(!transaction.split(",")[0].equals("CHARGE")) continue;

      String acc = transaction.split(",")[2];
      String code = transaction.split(",")[4];
      accountToTotalCharges.put(acc, accountToTotalCharges.getOrDefault(acc, 0) + 1);
      if(fraudCodeSet.contains(code)){
        accountToFraud.put(acc, accountToFraud.getOrDefault(acc, 0) + 1);
      }
    }

    List<String> ret = new ArrayList<>();
    for(String acc : accountToTotalCharges.keySet()){
      int total = accountToTotalCharges.get(acc);
      if(total >= Integer.parseInt(min)) {
        int fraud = accountToFraud.get(acc);
        String mcc = accountIdToMcc.get(acc);
        int threshold = mccToThresholds.get(mcc);
        if(fraud >= threshold){
          ret.add(acc);
        }
      }
    }
    Collections.sort(ret);
    return ret;
  }

  public static void main(String[] args) {
    String[] nonFraudCodes = {"approved", "invalid_pin", "expired_card"};
    String[] fraudCodes = {"do_not_honor", "stolen_card", "lost_card"};
    String[] mccThresholds = {"retail,5", "airline,2", "venue,3"};
    String[] merchantMCCs = {"acct_1,airline", "acct_2,venue", "acct_3,retail"};
    String minTransactions = "0";
    String[] transactions = {
        "CHARGE,ch_1,acct_1,100,do_not_honor",
        "CHARGE,ch_2,acct_1,200,approved",
        "CHARGE,ch_3,acct_1,300,do_not_honor",
        "CHARGE,ch_4,acct_2,100,lost_card",
        "CHARGE,ch_5,acct_2,200,lost_card",
        "CHARGE,ch_6,acct_2,300,lost_card",
        "CHARGE,ch_7,acct_3,100,lost_card",
        "CHARGE,ch_8,acct_2,200,stolen_card",
        "CHARGE,ch_9,acct_3,100,approved"
    };

    FraudDetection f = new FraudDetection();
    List<String> fraudulentMerchants = f.detectFraudulentMerchants(
        nonFraudCodes, fraudCodes, mccThresholds, merchantMCCs, minTransactions, transactions
    );
    System.out.println(String.join(",", fraudulentMerchants));
  }
}
