package Databricks.IPToCIDR;

import java.util.List;
import java.util.ArrayList;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class AllowDeny {

  public static boolean isIPAllowed(String ipAddress, List<String[]> rules) {
    try {
      InetAddress ip = InetAddress.getByName(ipAddress);
      for (String[] rule : rules) {
        String action = rule[0]; // "ALLOW" or "DENY"
        String cidr = rule[1];
        if (isIPInCIDR(ip, cidr)) {
          if (action.equals("ALLOW")) {
            return true;
          }
          if (action.equals("DENY")) {
            continue; // Skip if "DENY", check next rule
          }
        }
      }
    } catch (UnknownHostException e) {
      System.out.println("Invalid IP address");
    }
    return false; // Default is false if no ALLOW rule matches
  }

  private static boolean isIPInCIDR(InetAddress ip, String cidr) {
    try {
      String[] parts = cidr.split("/");
      String cidrIP = parts[0];
      int prefixLength = Integer.parseInt(parts[1]);
      byte[] ipBytes = ip.getAddress();
      byte[] cidrBytes = InetAddress.getByName(cidrIP).getAddress();

      int mask = (0xFFFFFFFF << (32 - prefixLength));
      int ipValue = 0;
      int cidrValue = 0;

      for (int i = 0; i < 4; i++) {
        ipValue = (ipValue << 8) | (ipBytes[i] & 0xFF); // left shift by 8 bits and deal with the unsigned bytes
        cidrValue = (cidrValue << 8) | (cidrBytes[i] & 0xFF);
      }

      return (ipValue & mask) == (cidrValue & mask);
    } catch (UnknownHostException | NumberFormatException e) {
      return false; // If CIDR is invalid
    }
  }

  public static void main(String[] args) {
    List<String[]> rules = new ArrayList<>();
    // Test cases to check various scenarios
    rules.add(new String[]{"ALLOW", "192.168.1.100/32"});
    rules.add(new String[]{"DENY", "192.168.1.0/24"});
    rules.add(new String[]{"ALLOW", "192.168.0.0/16"});
    rules.add(new String[]{"DENY", "10.0.0.0/8"});

    // Edge Case 1: Exact match for ALLOW
    System.out.println(isIPAllowed("192.168.1.100", rules)); // Expected: true

    // Edge Case 2: Exact match for DENY
    System.out.println(isIPAllowed("192.168.1.100", rules)); // Expected: true

    // Edge Case 3: ALLOW with multiple CIDR blocks
    System.out.println(isIPAllowed("10.1.2.3", rules)); // Expected: false

    // Edge Case 4: No matching ALLOW rule
    System.out.println(isIPAllowed("192.168.1.100", rules)); // Expected: true

    // Edge Case 5: IP matching a supernet but not a subnet
    System.out.println(isIPAllowed("192.168.1.100", rules)); // Expected: true

    // Edge Case 6: IP in multiple ALLOW rules
    rules.add(new String[]{"ALLOW", "192.168.1.100/32"});
    System.out.println(isIPAllowed("192.168.1.100", rules)); // Expected: true

    // Edge Case 7: IP on the boundary of CIDR
    System.out.println(isIPAllowed("192.168.1.255", rules)); // Expected: true

    // Edge Case 8: Large IP range for ALLOW
    rules.add(new String[]{"ALLOW", "10.0.0.0/8"});
    System.out.println(isIPAllowed("10.1.2.3", rules)); // Expected: true

    // Edge Case 9: Deny block on supernet and Allow block on subnet
    rules.add(new String[]{"ALLOW", "192.168.1.100/32"});
    System.out.println(isIPAllowed("192.168.1.100", rules)); // Expected: true

    // Edge Case 10: No CIDR rules
    System.out.println(isIPAllowed("192.168.1.100", new ArrayList<>())); // Expected: false

    // Edge Case 11: Matching IP in private range
    rules.add(new String[]{"ALLOW", "192.168.0.0/16"});
    rules.add(new String[]{"DENY", "192.168.0.0/24"});
    System.out.println(isIPAllowed("192.168.1.100", rules)); // Expected: true

    // Edge Case 12: IP address just outside a CIDR block
    System.out.println(isIPAllowed("192.168.2.1", rules)); // Expected: false
  }
}
