package Databricks.IPToCIDR;

import java.util.ArrayList;
import java.util.List;

public class IPToCIDR {

  // Converts the IP address into a list of CIDR blocks.
  // A CIDR block is represented as <IP_address>/<prefix_length>.
  // The first portion of the IP address is used to identify the network,
  // and the second portion identifies individual hosts within that network.

  public List<String> ipToCIDR(String ip, int n) {
    int curr = toInt(ip); // Step 1: Convert the IP address into an integer (for easier manipulation).
    List<String> ret = new ArrayList<>(); // Step 2: Initialize the list to store the resulting CIDR blocks.

    // Step 3: Loop until all `n` IPs are converted to CIDR blocks.
    while(n > 0) {

      // Step 4: Calculate the maximum block size possible at this point.
      // Get the number of trailing zeros in `curr`, which tells us the block size.
      int maxBits = Integer.numberOfTrailingZeros(curr);

      int bitVal = 1; // Step 5: Start with a block size of 1 IP address (CIDR /32).
      int count = 0;  // Step 6: This tracks how many bits are still available to vary in the host portion.

      // Step 7: Adjust the `bitVal` (block size) and `count` (number of bits used for the host portion)
      // to find the largest possible block that fits within the remaining `n` IP addresses.
      while(bitVal < n && count < maxBits) {
        bitVal <<= 1; // Shift `bitVal` left by 1 bit (multiply by 2).
        count++; // Increment `count`, tracking how many bits we've shifted.
      }

      // Step 8: If the `bitVal` exceeds the remaining `n` IPs, shift back and adjust `count`.
      if(bitVal > n) {
        bitVal >>= 1;  // Shift `bitVal` back right by 1 bit (divide by 2).
        count--;  // Adjust the bit count.
      }

      // Step 9: Add the current CIDR block to the result list.
      ret.add(toString(curr, 32 - count));  // Convert the integer `curr` back to the CIDR format.

      // Step 10: Update the remaining number of IPs (`n`) and the current IP address (`curr`).
      n -= bitVal; // Subtract the number of IPs we've just handled.
      curr += bitVal; // Move the current IP address forward by the block size.
    }

    // Step 11: Return the list of CIDR blocks.
    return ret;
  }

  // Helper method to convert an integer back to a string representation of an IP address in CIDR notation.
  public String toString(int number, int range) {
    final int WORD_SIZE = 8; // Define the size of each octet (8 bits).

    StringBuilder builder = new StringBuilder();  // StringBuilder for constructing the output.

    // Step 1: Loop through each byte in the IP address (4 bytes for IPv4).
    for (int i = 3; i >= 0; i--) {

      // Step 2: Shift the number and mask with `& 255` to get each byte of the IP.
      builder.append(Integer.toString((number >> (i * WORD_SIZE)) & 255));  // Shift the IP address and mask out all but the last 8 bits (one byte).
      builder.append('.');  // Append the dot separator.
    }

    // Step 3: Remove the trailing dot after the last byte.
    builder.setLength(builder.length() - 1);

    // Step 4: Append the CIDR prefix length (how many bits are used for the network portion).
    builder.append('/');
    builder.append(Integer.toString(range));

    // Step 5: Return the formatted CIDR string.
    return builder.toString();
  }

  // Helper method to convert an IP address string to an integer.
  // This is useful for bitwise operations.
  public int toInt(String ip) {
    // Step 1: Split the IP address string into its four octets.
    String[] sep = ip.split("\\.");
    int sum = 0;

    // Step 2: Convert each octet into an integer and shift it accordingly to form the final integer.
    for(int i = 0; i < sep.length; i++) {
      sum *= 256;  // Shift left by 8 bits (multiply by 256).
      sum += Integer.parseInt(sep[i]);  // Add the current octet to the sum.
    }
    // Step 3: Return the resulting integer representing the IP address.
    return sum;
  }

  // Main method to test the functionality.
  public static void main(String[] args) {
    IPToCIDR ipToCIDR = new IPToCIDR();  // Create an instance of the IPToCIDR class.

    // Test case: Convert the IP "255.0.0.7" and 10 IPs into CIDR blocks.
    String ip = "255.0.0.7";  // Starting IP address
    int n = 10;  // Number of IP addresses to be converted to CIDR blocks.

    // Step 1: Call the ipToCIDR method and print the result.
    System.out.println(ipToCIDR.ipToCIDR(ip, n));
  }
}
