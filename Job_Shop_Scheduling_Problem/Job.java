package Job_Shop_Scheduling_Problem;
// Created: September 2022
public class Job {
    private int jobId;
    private int[] processingTimes;
    public Job(int jobId, int[] processingTimes) {
        this.jobId = jobId;
        this.processingTimes = processingTimes;
    }
    public int getJobId() {
        return jobId;
    }
    public int[] getProcessingTimes() {
        return processingTimes;
    }
    public int getProcessingTime(int machineId) {
        if (machineId >= 0 && machineId < processingTimes.length) {
            return processingTimes[machineId];
        } else {
            return -1;
        }
    }
}