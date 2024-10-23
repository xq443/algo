package Twitter;

public class Tweet {
  public int tweetId;
  public int time;
  public Tweet next;

  public Tweet(int tweetId, int time) {
    this.tweetId = tweetId;
    this.next = null;
    this.time = time;
  }

  public int getTweetId() { return tweetId; }

  public Tweet getNext() { return next; }

  public int getTime() { return time; }
}
