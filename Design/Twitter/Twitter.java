package Twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Twitter {
  public int timestamp;

  /**
   * Initializes your twitter object.
   */
  public Map<Integer, User> userMap;
  public Twitter() {
    this.userMap = new HashMap<>();
    this.timestamp = 0;
  }

  /**
   * Composes a new tweet with ID tweetId by the user userId.
   * Each call to this function will be made with a unique tweetId.
   * @param userId
   * @param tweetId
   */
  public void postTweet(int userId, int tweetId) {
    if (!userMap.containsKey(userId)) {
      User newUser = new User(userId);
      userMap.put(userId, newUser);
    }
    userMap.get(userId).post(tweetId, timestamp++);
  }

  /**
   * Retrieves the 10 most recent tweet IDs in the user's news feed.
   * Each item in the news feed must be posted by users who the user followed or
   * by the user themselves. Tweets must be ordered from most recent to least
   * recent.
   * @param userId
   * @return
   */
  public List<Integer> getNewsFeed(int userId) {
    List<Integer> ret = new ArrayList<>();
    if (!userMap.containsKey(userId))
      return ret;

    Set<Integer> following = userMap.get(userId).following;
    PriorityQueue<Tweet> queue =
        new PriorityQueue<>((a, b) -> (b.time - a.time));

    for (int person : following) {
      Tweet tweet = userMap.get(person).head;
      if (tweet != null)
        queue.offer(tweet);
    }

    int count = 0;
    while (!queue.isEmpty() && count < 10) {
      Tweet tweet = queue.poll();
      ret.add(tweet.tweetId);
      count++;
      if (tweet.next != null) {
        queue.offer(tweet.next);
      }
    }
    return ret;
  }
  /**
   * The user with ID followerId started following the user with ID followeeId.
   * @param followerId
   * @param followeeId
   */
  public void follow(int followerId, int followeeId) {
    if (!userMap.containsKey(followerId)) {
      User newUser = new User(followerId);
      userMap.put(followerId, newUser);
    }
    if (!userMap.containsKey(followeeId)) {
      User newUser = new User(followeeId);
      userMap.put(followeeId, newUser);
    }
    userMap.get(followerId).follow(followeeId);
  }

  /**
   * The user with ID followerId started unfollowing the user with ID
   * followeeId.
   * @param followerId
   * @param followeeId
   */
  void unfollow(int followerId, int followeeId) {
    if (!userMap.containsKey(followerId) || followerId == followeeId)
      return;
    userMap.get(followerId).unfollow(followeeId);
  }
  public Map<Integer, User> getUserMap() { return userMap; }
}
