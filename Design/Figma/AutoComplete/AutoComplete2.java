package Figma.AutoComplete;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoComplete2 {

  // Method to find the longest common prefix from a list of files
  public String longestCommonPrefix(List<String> files) {
    if (files == null || files.isEmpty()) {
      return "";
    }

    // Start with the first file name as the initial prefix
    String prefix = files.get(0);

    // Iterate through the list of files to find the longest common prefix
    for (String file : files) {
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

  // Method to find the autocomplete result considering subfolders
  public String autoComplete(List<String> files, String input) {
    if (input == null || input.isEmpty()) {
      return "";
    }

    // If input ends with '/', it's considered a folder path
    boolean isFolderInput = input.endsWith("/");

    // Filter the files based on whether they start with the input
    List<String> matchingFiles = new ArrayList<>();
    for (String file : files) {
      if (isFolderInput) {
        // If the input is a folder, match only files within that folder
        if (file.startsWith(input)) {
          matchingFiles.add(file);
        }
      } else {
        // If input is not a folder, match files and subfolders that start with the input
        if (file.contains(input)) {
          matchingFiles.add(file);
        }
      }
    }

    // If there are no matching files, return an empty string
    if (matchingFiles.isEmpty()) {
      return "";
    }

    // Find the longest common prefix of the matching files
    String prefix = longestCommonPrefix(matchingFiles);

    // If the input is a folder path, ensure that the results also match the same folder
    if (isFolderInput && !prefix.endsWith("/")) {
      // Ensure it ends with '/'
      prefix += "/";
    }

    return prefix;
  }

  public static void main(String[] args) {
    AutoComplete2 autoComplete = new AutoComplete2();

    List<String> files2 = List.of("folder/file1", "folder/file2", "folder/file/new");
    String inputn = "f";
    System.out.println("Test case 0: " + autoComplete.autoComplete(files2, inputn)); // Expected Output: folder/file

    List<String> files = Arrays.asList(
        "folder2/file1",
        "folder/file2",
        "folder/file/new"
    );

    // Test case 1: Input is a folder path, files are in different folders
    String input1 = "folder2/";
    String result1 = autoComplete.autoComplete(files, input1);
    System.out.println("Test case 1: " + result1); // Expected: ""

    // Test case 2: Input is a file prefix, files are in different folders
    String input2 = "f";
    String result2 = autoComplete.autoComplete(files, input2);
    System.out.println("Test case 2: " + result2); // Expected: ""

    // Test case 3: Input is a folder, but no files match
    String input3 = "nonexistent/";
    String result3 = autoComplete.autoComplete(files, input3);
    System.out.println("Test case 3: " + result3); // Expected: ""

    // Test case 4: Input is empty
    String input4 = "";
    String result4 = autoComplete.autoComplete(files, input4);
    System.out.println("Test case 4: " + result4); // Expected: ""

    // Test case 5: Input is a folder path with files inside different folders
    String input5 = "file";
    List<String> files3 = Arrays.asList(
        "folder1/file1",
        "folder2/file2"
    );
    String result5 = autoComplete.autoComplete(files3, input5);
    System.out.println("Test case 5: " + result5); // Expected: ""
  }
}
