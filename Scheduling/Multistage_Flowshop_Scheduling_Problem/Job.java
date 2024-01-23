package Multistage_Flowshop_Scheduling_Problem;
// Created: September 2022
public class Job {
    private int id;
    private int[] processingTimes;
    public Job(int id, int[] processingTimes) {
        this.id = id;
        this.processingTimes = processingTimes;
    }
    public int getId() {
        return id;
    }
    public int[] getProcessingTimes() {
        return processingTimes;
    }
}