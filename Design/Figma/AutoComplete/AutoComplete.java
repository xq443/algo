package Figma.AutoComplete;

import java.util.List;

public class AutoComplete {

  // Method to find the longest common prefix from a list of files
  public String longestCommonPrefix(List<String> files, String input) {
    if (input == null || input.isEmpty()) {
      return "";
    }

    // Start with the first file name as the initial prefix
    String prefix = files.get(0);

    // Iterate through the list of files to find the longest common prefix
    for(String file : files) {
      while (!file.startsWith(prefix)) {
        // Shorten the prefix until it matches the start of the current file
        prefix = prefix.substring(0, prefix.length() - 1);
        if (prefix.isEmpty()) {
          return "";
        }
      }
    }
    return prefix;
  }

  // Method to return the appropriate autocomplete suggestion
  public String autoComplete(List<String> files, String input) {
    // Filter the files to include only those containing the input string
    List<String> filteredFiles = files.stream()
        .filter(file -> file.contains(input))
        .toList();

    if (filteredFiles.isEmpty()) {
      return "";
    }

    // Find the longest common prefix for the filtered files
    String commonPrefix = longestCommonPrefix(filteredFiles, input);
    int lastSlashIndex = commonPrefix.lastIndexOf('/'); // Find the index of the last occurrence of "/"

    if (lastSlashIndex != -1) {
      // Extract the folder path from the common prefix
      String folderPath = commonPrefix.substring(0, lastSlashIndex + 1);

      // Check if all filtered files are in the same folder and return folder/file if they are
      boolean allInSameFolder = filteredFiles.stream()
          .allMatch(file -> file.startsWith(folderPath));

      if (allInSameFolder) {
        // If all files are under the same folder/file path, return folder/file
        return folderPath;
      }
    }

    // If files are not in the same folder, return the common prefix
    return commonPrefix;
  }

  public static void main(String[] args) {
    AutoComplete autoComplete = new AutoComplete();

    // Test 1: Basic autocomplete for file names
    List<String> files = List.of("file1", "file2", "file_new");
    String input = "f";
    System.out.println(autoComplete.longestCommonPrefix(files, input)); // Expected Output: file

    // Test 2: Autocomplete for files inside a folder
    List<String> files2 = List.of("folder/file1", "folder/file2", "folder/file/new");
    String input2 = "f";
    System.out.println(autoComplete.autoComplete(files2, input2)); // Expected Output: folder/file
  }
}
