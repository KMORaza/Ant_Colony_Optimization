package Assembly_Sequence_Planning;
// Created: September 2022
public class Task {
    private String taskId;
    private int difficulty;
    public Task(String taskId, int difficulty) {
        this.taskId = taskId;
        this.difficulty = difficulty;
    }
    public String getTaskId() {
        return taskId;
    }
    public int getDifficulty() {
        return difficulty;
    }
}