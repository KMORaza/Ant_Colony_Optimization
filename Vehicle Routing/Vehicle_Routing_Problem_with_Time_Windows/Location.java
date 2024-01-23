package Vehicle_Routing_Problem_with_Time_Windows;
// Created: September 2022
public class Location {
    private int id;
    private int xCoordinate;
    private int yCoordinate;
    private int demand;
    private int startTime;
    private int endTime;
    public Location(int id, int xCoordinate, int yCoordinate, int demand, int startTime, int endTime) {
        this.id = id;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.demand = demand;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getxCoordinate() {
        return xCoordinate;
    }
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }
    public int getyCoordinate() {
        return yCoordinate;
    }
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
    public int getDemand() {
        return demand;
    }
    public void setDemand(int demand) {
        this.demand = demand;
    }
    public int getStartTime() {
        return startTime;
    }
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
    public int getEndTime() {
        return endTime;
    }
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}