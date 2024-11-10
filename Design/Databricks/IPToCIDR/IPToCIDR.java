package Databricks.IPToCIDR;

import java.util.ArrayList;
import java.util.List;

public class IPToCIDR {
  public List<String> ipToCIDR(String ip, int n) {
    int curr = toInt(ip); // convert into integer
    List<String> ret = new ArrayList<>();

    while(n > 0) {

      // Find the number of trailing zero bits in `curr` (used to calculate maximum block size)
      int maxBits = Integer.numberOfTrailingZeros(curr);

      int bitVal = 1; // the number of IP addresses in the current CIDR block
      int count = 0; // track how many bits in the IP address are left variable for the host portion of the CIDR block.

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

      // Shifting Bits and Masking with & 255 bc we zero out everything except the last 8 bits.
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
    String ip = "255.0.0.7"; // [255.0.0.7/32, 255.0.0.8/29, 255.0.0.16/32]
    int n = 10;
    System.out.println(ipToCIDR.ipToCIDR(ip, n));
  }
}
