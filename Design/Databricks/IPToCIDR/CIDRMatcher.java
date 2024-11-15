package Databricks.IPToCIDR;

import java.util.List;

public class CIDRMatcher {

  public boolean isIPInCIDR(String ip, List<String> cidrs) {
    int ipInt = toInt(ip); // Convert IP to integer to perform bitwise operations easier

    // Loop through each CIDR in the list
    for (String cidr : cidrs) {
      if (cidrMatch(ipInt, cidr)) {
        return true; // If IP matches the CIDR, return true
      }
    }

    return false; // If no match found, return false
  }

  // Converts an IP address to an integer
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

  // Check if the given IP matches the CIDR block
  private boolean cidrMatch(int ipInt, String cidr) {
    String[] parts = cidr.split("/");
    String baseIP = parts[0];
    int prefixLength = Integer.parseInt(parts[1]);

    int baseIPInt = toInt(baseIP);


    // Create mask based on prefix length
    // If the prefixLength is 24,
    // we need a mask that keeps the first 24 bits and sets the remaining 8 bits to 0.

    // Apply the mask based on the prefix length
    int mask = (0xFFFFFFFF << (32 - prefixLength)); // bitmask with 255: 11111111 11111111 11111111 11111111

    /**
     * Consider the IP 192.168.1.5 with a subnet mask of /24
     * ipInt for 192.168.1.5: 11000000 10101000 00000001 00000101 (integer representation of the IP)
     * baseIPInt for 192.168.1.0: 11000000 10101000 00000001 00000000 (integer representation of the network base IP)
     *
     * ipInt & mask = (11000000 10101000 00000001 00000101) & 11111111 11111111 11111111 00000000
     * = 11000000 10101000 00000001 00000000 (192.168.1.0)
     *
     * baseIPInt & mask = (11000000 10101000 00000001 00000000) & 11111111 11111111 11111111 00000000
     * = 11000000 10101000 00000001 00000000 (192.168.1.0)
     */

    // Compare the masked IP address with the base CIDR IP:
    // checks if the network portion of the ipInt matches the network portion of baseIPInt.

    // determining which part of an IP address belongs to the network (the part shared by all devices in the same network)
    // and which part belongs to the host (the unique part that identifies individual devices within the network).
    //  masked version of 192.168.1.100 matches 192.168.1.0, belongs to the network,
    return (ipInt & mask) == (baseIPInt & mask);

  }

  public static void main(String[] args) {
    CIDRMatcher matcher = new CIDRMatcher();

    // Edge Case 1: Minimum IP Address (0.0.0.0)
    System.out.println("Edge Case 1: " + matcher.isIPInCIDR("0.0.0.0", List.of("0.0.0.0/8"))); // Expected: true

    // Edge Case 2: Maximum IP Address (255.255.255.255)
    System.out.println("Edge Case 2: " + matcher.isIPInCIDR("255.255.255.255", List.of("255.255.255.255/32"))); // Expected: true

    // Edge Case 3: Boundary CIDR Matching (192.168.1.0/24)
    System.out.println("Edge Case 3: " + matcher.isIPInCIDR("192.168.1.0", List.of("192.168.1.0/24"))); // Expected: true
    System.out.println("Edge Case 3 (Outside Range): " + matcher.isIPInCIDR("192.168.2.0", List.of("192.168.1.0/24"))); // Expected: false

    // Edge Case 4: IP at the Edge of a Range (192.168.1.255, last address in 192.168.1.0/24)
    System.out.println("Edge Case 4: " + matcher.isIPInCIDR("192.168.1.255", List.of("192.168.1.0/24"))); // Expected: true

    // Edge Case 5: Private Network IP (Private Class A) 10.x.x.x
    System.out.println("Edge Case 5: " + matcher.isIPInCIDR("10.0.0.1", List.of("10.0.0.0/8"))); // Expected: true

    // Edge Case 6: Private Network IP (Private Class B) 172.16.x.x
    System.out.println("Edge Case 6: " + matcher.isIPInCIDR("172.16.0.1", List.of("172.16.0.0/12"))); // Expected: true

    // Edge Case 7: Private Network IP (Private Class C) 192.168.x.x
    System.out.println("Edge Case 7: " + matcher.isIPInCIDR("192.168.0.1", List.of("192.168.0.0/16"))); // Expected: true

    // Edge Case 8: CIDR with Larger Prefix Length (e.g., /32)
    System.out.println("Edge Case 8: " + matcher.isIPInCIDR("192.168.1.100", List.of("192.168.1.100/32"))); // Expected: true
    System.out.println("Edge Case 8 (Different IP): " + matcher.isIPInCIDR("192.168.1.101", List.of("192.168.1.100/32"))); // Expected: false

    // Edge Case 9: CIDR with Smaller Prefix Length (e.g., /8)
    System.out.println("Edge Case 9: " + matcher.isIPInCIDR("192.168.1.1", List.of("192.0.0.0/8"))); // Expected: true
    System.out.println("Edge Case 9 (Outside Range): " + matcher.isIPInCIDR("10.0.0.1", List.of("192.0.0.0/8"))); // Expected: false

    // Edge Case 10: Unaligned IP with CIDR (e.g., 10.10.10.10 in CIDR 10.10.10.128/25)
    System.out.println("Edge Case 10: " + matcher.isIPInCIDR("10.10.10.10", List.of("10.10.10.128/25"))); // Expected: false
  }
}

