package Figma.File;

import java.util.*;

class FileSystem {

  private Map<String, File> files;
  private Set<File> visited;

  public FileSystem() {
    files = new HashMap<>();
    visited = new HashSet<>();
  }

  public File createFile(String name) {
    File newFile = new File(name);
    files.put(name, newFile);
    return newFile;
  }

  public List<String> findTopmostAccessibleFiles(String user) {
    List<String> result = new ArrayList<>();
    List<File> sortedFiles = topologicalSort(user);

    // After topological sort, add files that the user can access and are topmost
    for (File file : sortedFiles) {
      if (file.canAccess(user) && isTopmost(file)) {
        result.add(file.name);
      }
    }

    return result;
  }

  private List<File> topologicalSort(String user) {
    List<File> sortedFiles = new ArrayList<>();
    visited.clear();

    // Perform DFS to find topologically sorted files
    for (File file : files.values()) {
      if (!visited.contains(file) && file.canAccess(user)) {
        dfs(file, sortedFiles);
      }
    }

    Collections.reverse(sortedFiles); // Topological sort gives us reverse order
    return sortedFiles;
  }

  private void dfs(File file, List<File> sortedFiles) {
    visited.add(file);

    for (File child : file.children) {
      if (!visited.contains(child)) {
        dfs(child, sortedFiles);
      }
    }

    sortedFiles.add(file);
  }

  private boolean isTopmost(File file) {
    // A file is topmost if it has no parent or its parent is not accessible
    return file.parent == null || !file.parent.canAccess(file.accessibleUsers.iterator().next());
  }

  public static void main(String[] args) {
    // Initialize the file system
    FileSystem fs = new FileSystem();

    // Create files
    File fileA = fs.createFile("fileA");
    File fileB = fs.createFile("fileB");
    File fileC = fs.createFile("fileC");
    File fileD = fs.createFile("fileD");

    // Set file hierarchy (parent-child relationship)
    fileA.addChild(fileB);  // fileB is a child of fileA
    fileC.addChild(fileD);  // fileD is a child of fileC

    // Add user access permissions
    fileA.addUserAccess("user1");
    fileC.addUserAccess("user1");

    // Before moving fileA
    System.out.println("Before moving fileA:");
    List<String> accessibleFilesBeforeMove = fs.findTopmostAccessibleFiles("user1");
    System.out.println(accessibleFilesBeforeMove); // Expected output: [fileA, fileC]

    // Move fileA under fileC (change hierarchy)
    fileC.addChild(fileA);  // Move fileA to be a child of fileC

    // After moving fileA
    System.out.println("\nAfter moving fileA:");
    List<String> accessibleFilesAfterMove = fs.findTopmostAccessibleFiles("user1");
    System.out.println(accessibleFilesAfterMove); // Expected output: [fileC]
  }
}
