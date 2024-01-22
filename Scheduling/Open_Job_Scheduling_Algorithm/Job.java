package Open_Job_Scheduling_Algorithm;
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
    public int getProcessingTime(int machineId) {
        return processingTimes[machineId];
    }
}