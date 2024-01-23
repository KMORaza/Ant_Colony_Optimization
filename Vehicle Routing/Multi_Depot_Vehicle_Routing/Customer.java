package Multi_Depot_Vehicle_Routing;
// Created: September 2022
public class Customer {
    private int id;
    private Location location;
    private double demand;
    public Customer(int id, Location location, double demand) {
        this.id = id;
        this.location = location;
        this.demand = demand;
    }
    public int getId() {
        return id;
    }
    public Location getLocation() {
        return location;
    }
    public double getDemand() {
        return demand;
    }
}