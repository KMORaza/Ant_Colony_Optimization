package Vehicle_Routing_Problem_with_Time_Windows;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Ant {
    private List<Location> tour;
    private double tourLength;
    public Ant() {
        this.tour = new ArrayList<>();
        this.tourLength = 0.0;
    }
    public void clearTour() {
        tour.clear();
        tourLength = 0.0;
    }
    public void visitLocation(Location location) {
        if (tour.isEmpty()) {
            tour.add(location);
        } else {
            Location lastLocation = tour.get(tour.size() - 1);
            tourLength += calculateDistance(lastLocation, location);
            tour.add(location);
        }
    }
    private double calculateDistance(Location location1, Location location2) {
        double dx = location1.getxCoordinate() - location2.getxCoordinate();
        double dy = location1.getyCoordinate() - location2.getyCoordinate();
        return Math.sqrt(dx * dx + dy * dy);
    }
    public List<Location> getTour() {
        return tour;
    }
    public double getTourLength() {
        return tourLength;
    }
}
