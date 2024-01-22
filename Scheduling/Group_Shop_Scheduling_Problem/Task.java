package Group_Shop_Scheduling_Problem;
// Created: September 2022
public class Task {
    private int taskId;
    private int duration;
    public Task(int taskId, int duration) {
        this.taskId = taskId;
        this.duration = duration;
    }
    public int getTaskId() {
        return taskId;
    }
    public int getDuration() {
        return duration;
    }
}