package Figma.AutoComplete;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AutoComplete3 {

  // Method to find the longest common prefix from a list of files
  public String longestCommonPrefix(List<String> files, String input) {
    if (input == null || input.isEmpty()) {
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

    // Filter files that start with the input
    List<String> matchingFiles = files.stream()
        .filter(file -> file.startsWith(input))
        .collect(Collectors.toList());

    if (matchingFiles.isEmpty()) {
      return "";
    }

    // Find the longest common prefix among the matching files
    String prefix = longestCommonPrefix(matchingFiles, input);

    // Check if all matching files are in the same folder
    String folder = null;
    for (String file : matchingFiles) {
      int lastSlashIndex = file.lastIndexOf('/');
      String currentFolder = (lastSlashIndex != -1) ? file.substring(0, lastSlashIndex) : "";
      if (folder == null) {
        folder = currentFolder;
      } else if (!folder.equals(currentFolder)) {
        return prefix;
      }
    }

    // If all matching files are in the same folder, return the folder path
    return folder + "/";
  }

  public static void main(String[] args) {
    AutoComplete3 autoComplete = new AutoComplete3();

    List<String> files2 = List.of("folder/file1", "folder/file2", "folder/file/new");
    String inputn = "folder/f";
    System.out.println("Test case 0: " + autoComplete.autoComplete(files2, inputn)); // Expected Output: folder/file

    List<String> files = Arrays.asList(
        "folder2/file/new/4/dl",
        "folder/file/new/1",
        "folder/file/new"
    );

    // Test case 1: Input matches files in the same folder
    String input1 = "file";
    String result1 = autoComplete.autoComplete(files, input1);
    System.out.println("Test case 1: " + result1);

    // Test case 2: Input matches files in different folders
    String input2 = "folder2/";
    String result2 = autoComplete.autoComplete(files, input2);
    System.out.println("Test case 2: " + result2);

    // Test case 3: Input matches no files
    String input3 = "nonexistent";
    String result3 = autoComplete.autoComplete(files, input3);
    System.out.println("Test case 3: " + result3); // Expected: ""

    // Test case 4: Input is empty
    String input4 = "";
    String result4 = autoComplete.autoComplete(files, input4);
    System.out.println("Test case 4: " + result4); // Expected: ""

    // Test case 5: Input matches files with common prefix but different folders
    String input5 = "file";
    List<String> files3 = Arrays.asList(
        "folder1/file1",
        "folder2/file2"
    );
    String result5 = autoComplete.autoComplete(files3, input5);
    System.out.println("Test case 5: " + result5); // Expected: "folder"
  }
}