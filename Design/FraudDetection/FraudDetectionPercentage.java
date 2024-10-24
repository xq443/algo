package FraudDetection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FraudDetectionPercentage {
  public List<String> detectFraudulentMerchants(
      String nonFraudCodes,
      String fraudCodes,
      List<String> mccThresholds,
      List<String> accountIdMcc,
      String min,
      List<String> charges) {

      //Set<String> nonFraudCodeSet = new HashSet<>(Arrays.asList(nonFraudCodes));
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
          int fraud = accountToFraud.getOrDefault(acc, 0);
          String mcc = accountIdToMcc.get(acc);
          double percentage = (double) fraud / total;
          double threshold = mccToThresholds.get(mcc);
          if(percentage >= threshold){
            ret.add(acc);
          }
        }
      }
      Collections.sort(ret);
      return ret;
    }

    public static void main(String[] args) {
      String nonFraudCodes = "approved,invalid_pin,expired_card"; // Single string with comma-separated values
      String fraudCodes = "do_not_honor,stolen_card,lost_card";   // Single string with comma-separated values
      List<String> mccThresholds = Arrays.asList(
          "retail,0.5", "airline,0.25", "venue,0.25", "restaurant,0.8" // List of strings for mccThresholds
      );
      List<String> merchantMCCs = Arrays.asList(
          "acct_1,airline", "acct_2,venue", "acct_3,venue" // List of strings for merchantMCCs
      );
      String minTransactions = "0"; // The minTransactions as a string that can represent integer or double
      List<String> transactions = Arrays.asList( // List of strings for transactions
          "CHARGE,ch_1,acct_1,100,do_not_honor",
          "CHARGE,ch_2,acct_1,200,approved",
          "CHARGE,ch_3,acct_1,300,do_not_honor",
          "CHARGE,ch_4,acct_2,100,approved",
          "CHARGE,ch_5,acct_2,200,approved",
          "CHARGE,ch_6,acct_1,300,lost_card",
          "CHARGE,ch_7,acct_2,100,approved",
          "CHARGE,ch_8,acct_2,200,approved",
          "CHARGE,ch_9,acct_3,100,approved",
          "CHARGE,ch_10,acct_3,100,approved",
          "CHARGE,ch_11,acct_3,100,approved",
          "CHARGE,ch_12,acct_3,100,stolen_card",
          "CHARGE,ch_13,acct_3,100,stolen_card",
          "CHARGE,ch_14,acct_2,100,stolen_card"
      );

      FraudDetectionPercentage f = new FraudDetectionPercentage();
      List<String> fraudulentMerchants = f.detectFraudulentMerchants(
          nonFraudCodes, fraudCodes, mccThresholds, merchantMCCs, minTransactions, transactions
      );
      System.out.println(String.join(",", fraudulentMerchants));
    }
  }
