package Figma.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class File {
  String name;
  Set<String> accessibleUsers;
  List<File> children;
  File parent;

  File(String name) {
    this.name = name;
    this.accessibleUsers = new HashSet<>();
    this.children = new ArrayList<>();
  }

  void addChild(File child) {
    children.add(child);
    child.parent = this;
  }

  void addUserAccess(String user) {
    accessibleUsers.add(user);
  }

  boolean canAccess(String user) {
    return accessibleUsers.contains(user);
  }
}