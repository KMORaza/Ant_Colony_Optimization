package Time_Dependent_Vehicle_Routing;
// Created: September 2022
import java.util.List;
public class DistanceMatrix {
    private double[][] distances;
    public DistanceMatrix(List<Coordinate> coordinates) {
        initializeDistances(coordinates);
    }
    private void initializeDistances(List<Coordinate> coordinates) {
        int numberOfLocations = coordinates.size();
        distances = new double[numberOfLocations][numberOfLocations];
        for (int i = 0; i < numberOfLocations; i++) {
            for (int j = 0; j < numberOfLocations; j++) {
                distances[i][j] = calculateDistance(coordinates.get(i), coordinates.get(j));
            }
        }
    }
    public double getDistance(int from, int to) {
        return distances[from][to];
    }
    private double calculateDistance(Coordinate coord1, Coordinate coord2) {
        double deltaX = coord1.getX() - coord2.getX();
        double deltaY = coord1.getY() - coord2.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}