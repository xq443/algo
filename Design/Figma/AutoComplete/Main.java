package Figma.AutoComplete;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  // Part1: Given a list of filenames and an input prefix, return the longest common prefix of all filenames that start with the input prefix.

  public static String part1(List<String> files, String input) {
    if (files == null || files.isEmpty()) return "";
//    List<String> matches = new ArrayList<>();
//    // Iterate through all files and filter out those that start with the input prefix
//    for (String file : files) {
//      if (file.startsWith(input)) {
//        matches.add(file);
//      }
//    }

    // If no files match, return an empty string
//    if (matches.isEmpty()) {
//      return "";
//    }

    // Initialize the longest common prefix as the first matching filename
    String prefix = files.get(0);
    // Compare each file and update the longest common prefix
    for (int i = 1; i < files.size(); i++) {
      prefix = commonPrefix(prefix, files.get(i));
      // If the common prefix is empty, end early
      if (prefix.isEmpty()) {
        return "";
      }
    }
    return prefix;
  }

  // Helper method: Find the longest common prefix between two strings.
  private static String commonPrefix(String s1, String s2) {
    int len = Math.min(s1.length(), s2.length());
    int idx = 0;
    // Compare characters from the start until they differ or reach the minimum length
    while (idx < len && s1.charAt(idx) == s2.charAt(idx)) {
      idx++;
    }
    return s1.substring(0, idx);
  }

  /**
   * Part2:
   * Given a list of file paths containing subdirectories and an input prefix without '/',
   * return the auto-completed path. If all matching files are in the same folder,
   * include the folder in the prefix.
   */
  public static String part2(List<String> paths, String input) {
    List<String> matches = new ArrayList<>();

    // Iterate through all paths and filter out those whose filenames start with the input prefix
    for (String path : paths) {
      String fileName = path.contains("/") ? path.substring(path.lastIndexOf("/") + 1) : path;
      if (fileName.startsWith(input)) {
        matches.add(path);
      }
    }

    if (matches.isEmpty()) {
      return "";
    }

    String folder = null;
    for (String path : matches) {
      int idx = path.lastIndexOf('/');
      String currentFolder = idx != -1 ? path.substring(0, idx) : "";
      if (folder == null) {
        folder = currentFolder;
      } else if (!folder.equals(currentFolder)) {
        folder = null;
        break; // No common folder
      }
    }

    // If files are in the same folder
    if (folder != null) {
      if (matches.size() == 1) {
        return folder; // Only one match, return folder
      } else { // multiple matches
        String common;
        if (folder.isEmpty()) { // just files
          common = part1(matches, input); // If folder is empty, find the common part
        } else {
          List<String> subPaths = pathWithoutFolder(matches, folder);
          common = part1(subPaths, input);
          if (!common.isEmpty()) {
            common = folder + "/" + common;
          }
        }
        return common;
      }
    } else {
      // If files are not in the same folder, return the longest common prefix of filenames
      return part1(matches, input);
    }
  }

  // Remove the folder part from the path list, keeping only the filenames.
  private static List<String> pathWithoutFolder(List<String> paths, String folder) {
    List<String> res = new ArrayList<>(paths.size());
    String prefix = folder.isEmpty() ? "" : folder + "/";
    for (String path : paths) {
      if (path.startsWith(prefix)) {
        res.add(path.substring(prefix.length()));
      }
    }
    return res;
  }

  // Part3: Handle input prefixes that may contain '/', implementing recursive auto-completion.
  public static String part3(List<String> paths, String input) {
    if (input.isEmpty()) {
      return "";
    }

    String[] parts = input.split("/");
    List<String> currentPaths = new ArrayList<>(paths);
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < parts.length; i++) {
      String part = parts[i];
      List<String> matches = new ArrayList<>(); // store paths that match the current part.
      // Iterate through the current path list and filter out those that start with the input part at the current level
      for (String path : currentPaths) {
        String[] pathParts = path.split("/");
        if (pathParts.length > i && pathParts[i].startsWith(part)) {
          matches.add(path);
        }
      }

      if (matches.isEmpty()) {
        break; // End early if no matches
      }

      // Extract sub-paths at the current level for common prefix calculation
      List<String> subPaths = extractSubPath(matches, i);
      String prefix = part1(subPaths, part);
      if (prefix.isEmpty()) {
        break;
      }

      // Build the result path
      if (!result.isEmpty()) {
        result.append("/");
      }
      result.append(prefix);

      currentPaths = matches; // Update the current path list to the matching paths
    }

    return result.toString();
  }

  // Helper method: Extract sub-paths at the specified level from the path list.
  private static List<String> extractSubPath(List<String> paths, int idx) {
    List<String> res = new ArrayList<>();
    for (String path : paths) {
      String[] parts = path.split("/");
      if (parts.length > idx) {
        res.add(parts[idx]);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    // Part1 测试用例
    System.out.println("Part1 Test:");
    List<String> files1 = Arrays.asList("file1", "file2", "file_new");
    System.out.println(part1(files1, "f")); // 预期输出: "file"

    List<String> files2 = Arrays.asList("apple", "app", "application");
    System.out.println(part1(files2, "app")); // 预期输出: "app"

    List<String> files3 = Arrays.asList("dog", "cat", "car");
    System.out.println(part1(files3, "c")); // 预期输出: ""

    List<String> files4 = Arrays.asList("test");
    System.out.println(part1(files4, "t")); // 预期输出: "test"

    List<String> files5 = new ArrayList<>();
    System.out.println(part1(files5, "a")); // 预期输出: ""

    // Part2 测试用例
    System.out.println("\nPart2 Test:");
    List<String> paths1 = Arrays.asList("folder/file1", "folder/file2", "folder/file_new");
    System.out.println(part2(paths1, "f")); // 预期输出: "folder/file"

    List<String> paths2 = Arrays.asList("folder1/file1", "folder2/file2", "folder1/file_new");
    System.out.println(part2(paths2, "fil")); // 预期输出: "f"

    List<String> paths3 = Arrays.asList("file1", "file2", "file_new");
    System.out.println(part2(paths3, "f")); // 预期输出: "file"

    List<String> paths4 = Arrays.asList("folder/sub/file1", "folder/sub/filew");
    System.out.println(part2(paths4, "f")); // 预期输出: "folder/sub/file"

    List<String> paths5 = Arrays.asList("folder/file1", "folder/file2", "folder2/file3");
    System.out.println(part2(paths5, "file")); // 预期输出: ""

    // Part3 测试用例
    System.out.println("\nPart3 Test:");
    List<String> pathsP3 = Arrays.asList(
        "home/foo/f1.txt",
        "home/foo/f1_2.txt",
        "home/foo/README",
        "home/foo/bar/f1.txt",
        "home/foo/bar/README"
    );
    System.out.println(part3(pathsP3, "f")); // 预期输出: ""
    System.out.println(part3(pathsP3, "home/f")); // 预期输出: "home/foo/f1"
    System.out.println(part3(pathsP3, "home/foo/b")); // 预期输出: "home/foo/bar"
    System.out.println(part3(pathsP3, "home/foo/bar/f")); // 预期输出: "home/foo/bar/f1"

    // 边界测试用例
    System.out.println("\n边界测试:");
    // 无匹配
    System.out.println(part1(files1, "z")); // 预期输出: ""
    System.out.println(part2(paths1, "z")); // 预期输出: ""
    System.out.println(part3(pathsP3, "unknown")); // 预期输出: ""

    // 所有文件完全匹配输入
    List<String> filesExact = Arrays.asList("exact");
    System.out.println(part1(filesExact, "exact")); // 预期输出: "exact"

    List<String> pathsExact = Arrays.asList("folder/exact");
    System.out.println(part2(pathsExact, "exact")); // 预期输出: "folder/exact"

    System.out.println(part3(pathsP3, "home/foo/README")); // 预期输出: "home/foo/README"
  }
}
