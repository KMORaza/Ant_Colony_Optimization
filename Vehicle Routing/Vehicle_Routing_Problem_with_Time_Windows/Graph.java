package Vehicle_Routing_Problem_with_Time_Windows;
// Created: September 2022
import java.util.List;
public class Graph {
    private double[][] distanceMatrix;
    private double[][] pheromoneMatrix;
    private List<Location> locations;
    public Graph(List<Location> locations) {
        this.locations = locations;
        int numLocations = locations.size();
        this.distanceMatrix = new double[numLocations][numLocations];
        this.pheromoneMatrix = new double[numLocations][numLocations];
        initializeDistanceMatrix(locations);
        initializePheromoneMatrix();
    }
    private void initializeDistanceMatrix(List<Location> locations) {
        for (int i = 0; i < locations.size(); i++) {
            for (int j = 0; j < locations.size(); j++) {
                if (i == j) {
                    distanceMatrix[i][j] = 0.0;
                } else {
                    Location location1 = locations.get(i);
                    Location location2 = locations.get(j);
                    distanceMatrix[i][j] = calculateDistance(location1, location2);
                }
            }
        }
    }
    private double calculateDistance(Location location1, Location location2) {
        double dx = location1.getxCoordinate() - location2.getxCoordinate();
        double dy = location1.getyCoordinate() - location2.getyCoordinate();
        return Math.sqrt(dx * dx + dy * dy);
    }
    private void initializePheromoneMatrix() {
        for (int i = 0; i < pheromoneMatrix.length; i++) {
            for (int j = 0; j < pheromoneMatrix[i].length; j++) {
                pheromoneMatrix[i][j] = 1.0;
            }
        }
    }
    public void updatePheromone(List<Ant> ants) {
        for (Ant ant : ants) {
            List<Location> antTour = ant.getTour();
            double antTourLength = ant.getTourLength();
            for (int i = 0; i < antTour.size() - 1; i++) {
                Location currentLocation = antTour.get(i);
                Location nextLocation = antTour.get(i + 1);
                int currentIdx = locations.indexOf(currentLocation);
                int nextIdx = locations.indexOf(nextLocation);
                pheromoneMatrix[currentIdx][nextIdx] += 1.0 / antTourLength;
            }
        }
    }
    public double[][] getDistanceMatrix() {
        return distanceMatrix;
    }
    public double[][] getPheromoneMatrix() {
        return pheromoneMatrix;
    }
}