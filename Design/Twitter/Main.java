package Twitter;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    Twitter twitter = new Twitter();
    twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
    List<Integer> ret =
        twitter.getNewsFeed(1); // User 1's news feed should return a list with
                                // 1 tweet id -> [5]. return [5]
    if (!ret.isEmpty()) {
      System.out.println("News feed should be:");
      for (int i = 0; i < ret.size(); i++) {
        System.out.println(ret.get(i));
      }
    }
    twitter.follow(1, 2);    // User 1 follows user 2.
    twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
    List<Integer> ret1 =
        twitter.getNewsFeed(1); // User 1's news feed should return a list with
                                // 2 tweet ids -> [6, 5].
    // Tweet id 6 should precede tweet id 5 because it is posted after tweet
    // id 5.
    if (!ret1.isEmpty()) {
      System.out.println("News feed should be:");
      for (int i = 0; i < ret1.size(); i++) {
        System.out.println(ret1.get(i));
      }
    }
  }
}
