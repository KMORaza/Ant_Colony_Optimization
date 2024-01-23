package Period_Vehicle_Routing;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Ant {
    private List<Integer> tour;
    private boolean[] visited;
    public Ant(int numLocations) {
        this.tour = new ArrayList<>();
        this.visited = new boolean[numLocations];
    }
    public void visit(int locationId) {
        tour.add(locationId);
        visited[locationId] = true;
    }
    public List<Integer> getTour() {
        return tour;
    }
    public boolean[] getVisited() {
        return visited;
    }
}