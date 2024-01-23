package Vehicle_Routing_Problem_with_Time_Windows;
// Created: September 2022
public class Vehicle {
    private int id;
    private int capacity;
    public Vehicle(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}