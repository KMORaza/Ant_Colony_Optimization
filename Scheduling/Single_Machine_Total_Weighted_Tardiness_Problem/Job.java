package Single_Machine_Total_Weighted_Tardiness_Problem;
// Created: September 2022
public class Job {
    private int id;
    private int processingTime;
    private int weight;
    public Job(int id, int processingTime, int weight) {
        this.id = id;
        this.processingTime = processingTime;
        this.weight = weight;
    }
    public int getId() {
        return id;
    }
    public int getProcessingTime() {
        return processingTime;
    }
    public int getWeight() {
        return weight;
    }
}