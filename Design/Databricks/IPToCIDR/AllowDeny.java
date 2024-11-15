package Databricks.IPToCIDR;

import java.util.List;
import java.util.ArrayList;

public class AllowDeny {
  // <IP_address>/<prefix_length>
  // the first 16 bits of the IP address represent the network portion
  // the remaining bits represent the host portion
  public static boolean isIPAllowed(String ipAddress, List<String[]> rules) {
    try {
      // Convert string IP address to integer representation
      int ipValue = convertIPToInt(ipAddress);

      for (String[] rule : rules) {
        String action = rule[0]; // "ALLOW" or "DENY"
        String cidr = rule[1];

        // Check if the IP is in the CIDR block
        if (isIPInCIDR(ipValue, cidr)) {
          if (action.equals("ALLOW")) {
            return true;
          }
          if (action.equals("DENY")) {
            continue; // Skip if "DENY", check next rule
          }
        }
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid IP address");
    }
    return false; // Default is false if no ALLOW rule matches
  }

  // Convert IP string to integer
  private static int convertIPToInt(String ip) {
    String[] parts = ip.split("\\."); // cidrIP = "192.168.1.100" -> ["192", "168", "1", "100"]
    if (parts.length != 4) {
      throw new IllegalArgumentException("Invalid IP address format");
    }
    int ipValue = 0;
    // Java's byte type is signed, meaning it can hold values from -128 to 127.
    // However, an IP address octet is always between 0 and 255, which is outside the signed byte range.
    // So, when converting from an integer to a byte or handling byte values,
    // we use & 0xFF to ensure we get the correct unsigned byte value (in the range 0–255).
    for (int i = 0; i < 4; i++) { // a byte value is treated as an unsigned value: 11111111
      ipValue = (ipValue << 8) | (Integer.parseInt(parts[i]) & 0xFF); // Left shift and combine into one integer
    } // For IP: ipValue = 192 * 256^3 + 168 * 256^2 + 1 * 256^1 + 100 = 3238992164
    return ipValue;
  }

  private static boolean isIPInCIDR(int ipValue, String cidr) {
    try {
      String[] parts = cidr.split("/");
      String cidrIP = parts[0];
      int prefixLength = Integer.parseInt(parts[1]); // 192.168.1.100/32 prefix length 32

      int cidrValue = convertIPToInt(cidrIP);  // cidrValue = 192 * 256^3 + 168 * 256^2 + 1 * 256^1 + 100 = 3238992164.

      // Create mask based on prefix length
      // If the prefixLength is 24,
      // we need a mask that keeps the first 24 bits and sets the remaining 8 bits to 0.
      int mask = (0xFFFFFFFF << (32 - prefixLength));  // (32 bits set to 1)

      // Check if the IP is in the CIDR range
      // determining which part of an IP address belongs to the network (the part shared by all devices in the same network)
      // and which part belongs to the host (the unique part that identifies individual devices within the network).

      //  masked version of 192.168.1.100 matches 192.168.1.0, belongs to the network,
      return (ipValue & mask) == (cidrValue & mask);
    } catch (NumberFormatException e) {
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
