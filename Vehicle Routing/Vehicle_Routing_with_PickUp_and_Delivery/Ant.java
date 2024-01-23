package Vehicle_Routing_with_PickUp_and_Delivery;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Ant {
    private List<Integer> tour;
    private boolean[] visited;
    public Ant(int numNodes) {
        this.tour = new ArrayList<>();
        this.visited = new boolean[numNodes];
    }
    public void visitNode(int node) {
        tour.add(node);
        visited[node] = true;
    }
    public List<Integer> getTour() {
        return tour;
    }
    public boolean hasVisited(int node) {
        return visited[node];
    }
}