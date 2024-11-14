package Figma.File;

import java.util.HashSet;
import java.util.Set;

class Permission {
  Set<User> allowedUsers;

  public Permission() {
    this.allowedUsers = new HashSet<>();
  }

  public void grantAccess(User user) {
    allowedUsers.add(user);
  }

  public boolean hasAccess(User user) {
    return allowedUsers.contains(user);
  }
}