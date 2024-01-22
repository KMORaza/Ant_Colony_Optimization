package Sequential_Ordering_Problem;
// Created: September 2022
public class Task {
    private static int nextTaskId = 0;
    private int taskId;
    public Task() {
        this.taskId = nextTaskId++;
    }
    public int getTaskId() {
        return taskId;
    }
}
