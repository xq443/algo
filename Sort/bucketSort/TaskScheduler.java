import java.util.PriorityQueue;

public class TaskScheduler {
    public static void main(String[] args) {
        // Create a priority queue to store tasks with length in desc order
        PriorityQueue<String> taskQueue = new PriorityQueue<>(
                (task1, task2) -> task2.length() - task1.length()
        );


        // Add tasks with priorities to the queue
        taskQueue.offer("A");
        taskQueue.offer("Ldm");
        taskQueue.offer("BC");
        if(taskQueue.size() > 2) taskQueue.poll();


        // Process tasks based on priority
        while (!taskQueue.isEmpty()) {
            System.out.println("Processing: " + taskQueue.poll());
        }
    }
}
