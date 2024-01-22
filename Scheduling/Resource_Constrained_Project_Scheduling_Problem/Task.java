package Resource_Constrained_Project_Scheduling_Problem;
// Created: September 2022
public class Task {
    private int id;
    private int duration;
    public Task(int id, int duration) {
        this.id = id;
        this.duration = duration;
    }
    public int getId() {
        return id;
    }
    public int getDuration() {
        return duration;
    }
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", duration=" + duration +
                '}';
    }
}
