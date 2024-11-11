package NVIDIA;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TinyURL {

  // Maps to store short URLs and their corresponding long URLs and expiration times
  // shortUrlKeyMap: This map stores the short URL as the key and the UrlEntry (which includes the long URL and expiration time) as the value.
  // It allows quick lookup of the long URL and its expiration time when given a short URL. This is essential for the decode and deleteUrl methods.
  private Map<String, UrlEntry> shortUrlKeyMap = new HashMap<>();

  // longUrlKeyMap: This map stores the long URL as the key and the short URL as the value.
  // It helps in checking if a long URL has already been shortened, ensuring that the same long URL always maps to the same short URL.
  // This is useful in the encode method to avoid generating multiple short URLs for the same long URL.
  private Map<String, String> longUrlKeyMap = new HashMap<>();
  private static final String chars = "0123456789ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz";

  /**
   * Generates a short URL for the given long URL with an optional expiration time.
   *
   * @param longUrl        The original long URL.
   * @param expirationTime The expiration time for the short URL. Defaults to 6 months if null.
   * @return The generated short URL.
   */
  public String encode(String longUrl, LocalDateTime expirationTime) {
    // Check if the long URL already has a short URL
    if (longUrlKeyMap.containsKey(longUrl)) {
      return longUrlKeyMap.get(longUrl);
    }

    // Generate a unique short URL: If the short URL already exists, the loop generates a new one and checks again.
    // This process repeats until a unique short URL is found.
    String shortUrl = getShortUrl();
    while (shortUrlKeyMap.containsKey(shortUrl)) {
      shortUrl = getShortUrl();
    }

    // Set default expiration time to 6 months if not provided
    if (expirationTime == null) {
      expirationTime = LocalDateTime.now().plusMonths(6);
    }

    // Store the URL and expiration time
    UrlEntry urlEntry = new UrlEntry(longUrl, expirationTime);
    shortUrlKeyMap.put(shortUrl, urlEntry);
    longUrlKeyMap.put(longUrl, shortUrl);
    return shortUrl;
  }

  /**
   * Retrieves the long URL for the given short URL if it has not expired.
   *
   * @param shortUrl The short URL.
   * @return The original long URL or an error message if expired or not found.
   */
  public String decode(String shortUrl) {
    UrlEntry urlEntry = shortUrlKeyMap.get(shortUrl);
    if (urlEntry != null && LocalDateTime.now().isBefore(urlEntry.expirationTime)) {
      return urlEntry.longUrl;
    }
    return "Error: URL has expired or does not exist.";
  }

  /**
   * Deletes the given short URL.
   *
   * @param shortUrl The short URL to delete.
   * @return A message indicating success or failure.
   */
  public String deleteUrl(String shortUrl) {
    UrlEntry urlEntry = shortUrlKeyMap.remove(shortUrl);
    if (urlEntry != null) {
      longUrlKeyMap.remove(urlEntry.longUrl);
      return "Short URL deleted successfully.";
    }
    return "Error: Short URL not found.";
  }

  /**
   * Generates a random 6-character short URL.
   *
   * @return The generated short URL.
   */
  private String getShortUrl() {
    char[] code = new char[6];
    Random random = new Random();
    for (int i = 0; i < 6; i++) {
      code[i] = chars.charAt(random.nextInt(chars.length()));
    }
    return "http://tinyurl.com/" + new String(code);
  }

  // Class to store the long URL and its expiration time
  public class UrlEntry {

    String longUrl;
    LocalDateTime expirationTime;

    UrlEntry(String longUrl, LocalDateTime expirationTime) {
      this.longUrl = longUrl;
      this.expirationTime = expirationTime;
    }
  }

  public static void main(String[] args) {
    TinyURL tinyURL = new TinyURL();

    // Test case 1: Default expiration time (6 months)
    String shortUrl1 = tinyURL.encode("https://example.com/very/long/url", null);
    System.out.println("Short URL: " + shortUrl1);
    System.out.println("Long URL: " + tinyURL.decode(shortUrl1));

    // Test case 2: Custom expiration time
    LocalDateTime customExpiration = LocalDateTime.now().plusDays(10);
    String shortUrl2 = tinyURL.encode("https://example.com/another/long/url", customExpiration);
    System.out.println("Short URL: " + shortUrl2);
    System.out.println("Long URL: " + tinyURL.decode(shortUrl2));

    // Test case 3: Deleting a short URL
    System.out.println(tinyURL.deleteUrl(shortUrl1));
    System.out.println(tinyURL.decode(shortUrl1)); // Should return an error message
  }
}