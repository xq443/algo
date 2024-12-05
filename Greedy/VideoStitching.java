import java.util.Arrays;

public class VideoStitching {
  public int videoStitching(int[][] clips, int time) {
//    Arrays.sort(clips, (a, b) -> (a[0] - b[0]));
    Arrays.sort(clips, (a, b) -> {
      if(a[0] != b[0]) {
        return a[0] - b[0];
      } else {
        return b[1] - a[1];
      }
    });

    int ret = 0;
    int end = 0, nextEnd = 0;
    int index = 0;

    while(end < time) {
      while(index < clips.length && clips[index][0] <= end) {
        nextEnd = Math.max(nextEnd, clips[index][1]);
        index++;
      }
      if(end == nextEnd) return -1;

      end = nextEnd;
      ret++;
      if(end >= time) return ret;
    }
    return -1;
  }

  public static void main(String[] args) {
    VideoStitching v =  new VideoStitching();
    int[][] clips = {{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}};
    System.out.println(v.videoStitching(clips, 10));
  }

}
