import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class exclusiveTimeofFunctions {
    //{function_id}:{"start" | "end"}:{timestamp}"
    protected class Log {
        protected int id;
        protected boolean isStart;
        protected int time;

        private Log(String logs) {
            String[] content = logs.split(":");
            this.id = Integer.parseInt(content[0]);
            this.isStart = content[1].equals("start");
            this.time = Integer.parseInt(content[2]);
        }
    }
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ret = new int[n];
        Stack<Integer> stack = new Stack<>();
        int lastEndTime = 0;
        for(String log : logs){
            Log logObj = new Log(log);
            if(logObj.isStart){
                if(!stack.isEmpty()){
                    int id = stack.peek();
                    ret[id] += logObj.time - lastEndTime;
                }
                lastEndTime = logObj.time;
                stack.push(logObj.id);
            }else{
                int id = stack.pop();
                ret[id] += logObj.time - lastEndTime + 1;
                lastEndTime = logObj.time + 1;
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        //test
        exclusiveTimeofFunctions e = new exclusiveTimeofFunctions();
        int n = 2;
        List<String> logs = List.of(new String[]{"0:start:0", "1:start:2", "1:end:5", "0:end:6"});
        System.out.println(Arrays.toString(e.exclusiveTime(n, logs)));

        int n1 = 1;
        List<String> logs1 = List.of(new String[]{"0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"});
        System.out.println(Arrays.toString(e.exclusiveTime(n1, logs1)));
    }
}
