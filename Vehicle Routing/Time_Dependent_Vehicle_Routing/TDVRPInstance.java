package Time_Dependent_Vehicle_Routing;
// Created: September 2022
import java.util.List;
public class TDVRPInstance {
    private List<Coordinate> coordinates;
    public TDVRPInstance(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
    public List<Coordinate> getCoordinates() {
        return coordinates;
    }
    public TDVRPInstance(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getNumberOfLocations() {
        return locations.size();
    }
    public List<Integer> getLocations() {
        return locations;
    }
    public int[][] getDistances() {
        return distances;
    }
    public int getDistance(int from, int to) {
        return distances[from][to];
    }
}
