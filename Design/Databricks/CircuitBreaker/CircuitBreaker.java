package Databricks.CircuitBreaker;

import java.util.function.Function;

public class CircuitBreaker {

  // Circuit Breaker 状态定义
  private static final int FAIL_THRESHOLD = 3;  // 失败阈值
  private static final int REJECT_THRESHOLD = 3; // 拒绝阈值
  private static final int RESET_THRESHOLD = 3;  // 重置阈值

  // 定义两个服务器的状态
  private Server primary;
  private Server secondary;

  public CircuitBreaker() {
    primary = new Server("Primary");
    secondary = new Server("Secondary");
  }

  // 模拟一个请求尝试
  public String handleRequest(Function<String, String> requestHandler) {
    // 首先尝试 primary server
    String response = attemptRequest(primary, requestHandler);
    if (response.equals("failure")) {
      // 如果 primary 失败，则尝试 secondary server
      response = attemptRequest(secondary, requestHandler);
    }
    return response;
  }

  // 尝试请求处理，返回处理结果
  private String attemptRequest(Server server, Function<String, String> requestHandler) {
    if (server.isCircuitOpen()) {
      return "rejected";
    }

    String result = requestHandler.apply(server.getName());
    if (result.equals("failure")) {
      server.incrementFailCount();
    } else {
      server.resetFailCount();
    }

    if (server.getFailCount() >= FAIL_THRESHOLD) {
      server.setCircuitOpen(true);
    }

    return result;
  }

  // 服务器类，用来模拟 Primary 和 Secondary Server
  private class Server {
    private String name;
    private int failCount;
    private int rejectCount;
    private boolean circuitOpen;

    public Server(String name) {
      this.name = name;
      this.failCount = 0;
      this.rejectCount = 0;
      this.circuitOpen = false;
    }

    public String getName() {
      return name;
    }

    public int getFailCount() {
      return failCount;
    }

    public void incrementFailCount() {
      this.failCount++;
    }

    public void resetFailCount() {
      this.failCount = 0;
    }

    public void setCircuitOpen(boolean circuitOpen) {
      this.circuitOpen = circuitOpen;
    }

    public boolean isCircuitOpen() {
      if (circuitOpen && rejectCount >= REJECT_THRESHOLD) {
        resetRejectCount();
        return false; // 可以重试
      }
      return circuitOpen;
    }

    public void incrementRejectCount() {
      this.rejectCount++;
    }

    public void resetRejectCount() {
      this.rejectCount = 0;
    }
  }

  public static void main(String[] args) {
    // Mock 请求
    CircuitBreaker circuitBreaker = new CircuitBreaker();

    // 模拟请求处理函数
    Function<String, String> requestHandler = (serverName) -> {
      // 随机模拟请求结果
      double rand = Math.random();
      if (rand < 0.7) {
        return "failure"; // 模拟 70% 的请求失败
      } else {
        return "success"; // 30% 请求成功
      }
    };

    // 连续请求尝试
    for (int i = 0; i < 20; i++) {
      String response = circuitBreaker.handleRequest(requestHandler);
      System.out.println("Response: " + response);
    }
  }
}
