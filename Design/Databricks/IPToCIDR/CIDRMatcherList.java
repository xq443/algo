package Databricks.IPToCIDR;

import java.util.List;

public class CIDRMatcherList {

  // Method to return the CIDR block that matches the given IP address
  public String getMatchingCIDR(String ip, List<String> cidrs) {
    int ipInt = toInt(ip); // Convert IP to integer to perform bitwise operations easier

    // Loop through each CIDR in the list
    for (String cidr : cidrs) {
      if (cidrMatch(ipInt, cidr)) {
        return cidr; // Return the CIDR block if the IP matches
      }
    }

    return null; // Return null if no CIDR block matches
  }

  // Converts an IP address to an integer
  public int toInt(String ip) {
    String[] sep = ip.split("\\.");
    int sum = 0;
    for (int i = 0; i < sep.length; i++) {
      sum *= 256; // Shift sum left by 8 bits
      sum += Integer.parseInt(sep[i]);
    }
    return sum;
  }

  // Check if the given IP matches the CIDR block
  private boolean cidrMatch(int ipInt, String cidr) {
    String[] parts = cidr.split("/");
    String baseIP = parts[0];
    int prefixLength = Integer.parseInt(parts[1]);

    int baseIPInt = toInt(baseIP);

    // Apply the mask based on the prefix length
    int mask = (int) (0xFFFFFFFF << (32 - prefixLength));

    // Compare the masked IP address with the base CIDR IP
    return (ipInt & mask) == (baseIPInt & mask);
  }

  public static void main(String[] args) {
    CIDRMatcherList matcher = new CIDRMatcherList();

    // Sample CIDR blocks
    List<String> cidrs = List.of("192.168.1.0/24", "10.0.0.0/8", "172.16.0.0/12");

    // Test cases
    System.out.println("Matching CIDR: " + matcher.getMatchingCIDR("192.168.1.10", cidrs)); // Expected: 192.168.1.0/24
    System.out.println("Matching CIDR: " + matcher.getMatchingCIDR("10.1.2.3", cidrs)); // Expected: 10.0.0.0/8
    System.out.println("Matching CIDR: " + matcher.getMatchingCIDR("172.16.5.5", cidrs)); // Expected: 172.16.0.0/12
    System.out.println("Matching CIDR: " + matcher.getMatchingCIDR("192.168.2.100", cidrs)); // Expected: null (no match)
  }
}
/**
 * A CIDR (Classless Inter-Domain Routing) block is a method used to specify IP addresses and their associated network masks.
 * IP_Address/Prefix_Length
 */