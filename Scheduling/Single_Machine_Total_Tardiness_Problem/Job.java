package Single_Machine_Total_Tardiness_Problem;
// Created: September 2022
// Job.java
public class Job {
    private int id;
    private int processingTime;
    private int dueDate;
    public Job(int id, int processingTime, int dueDate) {
        this.id = id;
        this.processingTime = processingTime;
        this.dueDate = dueDate;
    }
    public int getId() {
        return id;
    }
    public int getProcessingTime() {
        return processingTime;
    }
    public int getDueDate() {
        return dueDate;
    }
}