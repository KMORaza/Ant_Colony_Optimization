package Multi_Depot_Vehicle_Routing;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Vehicle {
    private int id;
    private double capacity;
    private List<Customer> route;
    public Vehicle(int id, double capacity) {
        this.id = id;
        this.capacity = capacity;
        this.route = new ArrayList<>();
    }
    public int getId() {
        return id;
    }
    public double getCapacity() {
        return capacity;
    }
    public List<Customer> getRoute() {
        return route;
    }
    public void addToRoute(Customer customer) {
        route.add(customer);
    }
}