package Databricks.IPToCIDR;

import java.util.ArrayList;
import java.util.List;

public class IPToCIDR {
  public List<String> ipToCIDR(String ip, int n) {
    int curr = toInt(ip);
    List<String> ret = new ArrayList<>();

    while(n > 0) {
      int maxBits = Integer.numberOfTrailingZeros(curr);

      int bitVal = 1;
      int count = 0;

      while(bitVal < n && count < maxBits) {
        bitVal <<= 1;
        count++;
      }

      if(bitVal > n) {
        bitVal >>= 1;
        count--;
      }

      ret.add(toString(curr, 32 - count));

      n -= bitVal;
      curr += bitVal;
    }

    return ret;

  }

  public String toString(int number, int range) {
    final int WORD_SIZE = 8;
    StringBuilder builder = new StringBuilder();

    for (int i = 3; i >= 0; i--) {
      builder.append(Integer.toString((number >> (i * WORD_SIZE)) & 255));
      builder.append('.');
    }

    // remove the extra dot at the end
    builder.setLength(builder.length() - 1);

    // append the CIDR prefix length
    builder.append('/');
    builder.append(Integer.toString(range));

    return builder.toString();
  }

  // convert an IP address string to an integer
  public int toInt(String ip) {
    // split by dot
    String[] sep = ip.split("\\.");
    int sum = 0;
    for(int i = 0; i < sep.length; i++) {
      sum *= 256; // Shift sum left by 8 bits
      sum += Integer.parseInt(sep[i]);
    }
    return sum;
  }

  public static void main(String[] args) {
    IPToCIDR ipToCIDR = new IPToCIDR();
    String ip = "255.0.0.7";
    int n = 10;
    System.out.println(ipToCIDR.ipToCIDR(ip, n));
  }
}
