package Period_Vehicle_Routing;
// Created: September 2022
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
public class Graph {
    private Map<Integer, Location> locations;
    public Graph() {
        this.locations = new HashMap<>();
    }
    public void addLocation(Location location) {
        locations.put(location.getId(), location);
    }
    public Location getLocation(int id) {
        return locations.get(id);
    }
    public Collection<Location> getLocations() {
        return locations.values();
    }
}