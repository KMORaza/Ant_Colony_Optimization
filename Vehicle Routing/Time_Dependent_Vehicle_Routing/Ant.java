package Time_Dependent_Vehicle_Routing;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Ant {
    private List<Integer> tour;
    public Ant(int numberOfLocations) {
        this.tour = new ArrayList<>(numberOfLocations);
    }
    public void constructTour(PheromoneMatrix pheromoneMatrix, DistanceMatrix distanceMatrix, TDVRPInstance tdvrpInstance) {
        int numberOfLocations = tdvrpInstance.getNumberOfLocations();
        int currentLocation = selectInitialLocation();
        for (int step = 0; step < numberOfLocations; step++) {
            List<Integer> availableLocations = getAvailableLocations(currentLocation, tour, tdvrpInstance);
            int nextLocation = selectNextLocation(availableLocations, pheromoneMatrix, distanceMatrix, currentLocation);
            tour.add(nextLocation);
            currentLocation = nextLocation;
        }
    }
    private int selectInitialLocation() {
        return tour.get(0);
    }
    private List<Integer> getAvailableLocations(int currentLocation, List<Integer> tour, TDVRPInstance tdvrpInstance) {
        List<Integer> allLocations = tdvrpInstance.getLocations();
        List<Integer> visitedLocations = tour;
        allLocations.removeAll(visitedLocations);
        return allLocations;
    }
    private int selectNextLocation(List<Integer> availableLocations, PheromoneMatrix pheromoneMatrix, DistanceMatrix distanceMatrix, int currentLocation) {
        Random random = new Random();
        return availableLocations.get(random.nextInt(availableLocations.size()));
    }
    public List<Integer> getTour() {
        return tour;
    }
}