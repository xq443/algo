package Twitter;

import java.util.HashSet;
import java.util.Set;

public class User {
  public int userId;
  public Set<Integer> following;
  public Tweet head;

  public User(int userId) {
    this.userId = userId;
    this.following = new HashSet<>();
    this.head = null;
    follow(userId);
  }
  public void follow(int followerId) { following.add(followerId); }
  public void unfollow(int followerId) { following.remove(followerId); }
  public void post(int id, int timestamp) {
    Tweet newTweet = new Tweet(id, timestamp);
    newTweet.next = head;
    head = newTweet;
  }
}
