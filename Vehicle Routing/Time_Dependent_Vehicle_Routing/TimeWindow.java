package Time_Dependent_Vehicle_Routing;
// Created: September 2022
public class TimeWindow {
    private int startTime;
    private int endTime;
    public TimeWindow(int startTime, int endTime) {
        if (startTime < 0 || endTime < 0 || startTime >= endTime) {
            throw new IllegalArgumentException("Invalid time window parameters");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public boolean isWithinWindow(int time) {
        return time >= startTime && time <= endTime;
    }
    public int getStartTime() {
        return startTime;
    }
    public int getEndTime() {
        return endTime;
    }
}