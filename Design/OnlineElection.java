import java.util.HashMap;
import java.util.Map;

public class OnlineElection {
  // map: person -> votes
  Map<Integer, Integer> map;
  int[] persons;
  int[] lead;

  int[] times;

  /**
   * TopVotedCandidate(int[] persons, int[] times) : Initializes the object with
   * the persons and times arrays.
   * @param persons
   * @param times
   */
  public OnlineElection(int[] persons, int[] times) {
    this.map = new HashMap<>();
    this.persons = persons;
    this.times = times;
    this.lead = new int[persons.length];
    int maxVotes = 0, leader = -1;
    for (int i = 0; i < persons.length; i++) {
      int person = persons[i];
      int votes = map.getOrDefault(person, 0) + 1;
      map.put(person, votes);

      if (maxVotes <= votes) {
        maxVotes = votes;
        leader = person;
      }
      lead[i] = leader;
    }
  }

  /**
   * int q(int t) Returns the number of the person that was leading the election
   * at time t according to the mentioned rules.
   * @param t
   * @return
   */

  public int q(int t) {
    int left = 0, right = persons.length - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (times[mid] == t)
        return lead[mid];
      else if (times[mid] > t) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return times[left] <= t ? lead[left] : lead[left - 1];
  }

  public static void main(String[] args) {
    int[] persons = {0, 1, 2, 2, 1};
    int[] times = {20, 28, 29, 54, 56};

    OnlineElection obj = new OnlineElection(persons, times);

    int[] queries = {28, 53, 57};
    for (int q : queries) {
      System.out.println(obj.q(q));
    }
  }
}
