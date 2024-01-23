package Multi_Depot_Vehicle_Routing;
// Created: September 2022
public class Depot {
    private int id;
    private Location location;
    public Depot(int id, Location location) {
        this.id = id;
        this.location = location;
    }
    public int getId() {
        return id;
    }
    public Location getLocation() {
        return location;
    }
}