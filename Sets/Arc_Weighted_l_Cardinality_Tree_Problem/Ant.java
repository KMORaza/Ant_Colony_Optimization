package Arc_Weighted_l_Cardinality_Tree_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Ant {
    private Graph graph;
    private List<Node> tour;
    public Ant(Graph graph) {
        this.graph = graph;
        this.tour = new ArrayList<>();
    }
    public void findTour() {
        Random random = new Random();
        Node startNode = graph.getNodes().get(random.nextInt(graph.getNodes().size()));
        tour.add(startNode);
        while (tour.size() < graph.getNodes().size()) {
            Node current = tour.get(tour.size() - 1);
            List<Node> neighbors = graph.getNeighbors(current);
            neighbors.removeAll(tour);
            if (!neighbors.isEmpty()) {
                Node nextNode = neighbors.get(random.nextInt(neighbors.size()));
                tour.add(nextNode);
            } else {
                tour.clear();
                tour.add(startNode);
            }
        }
    }
    public List<Node> getTour() {
        return tour;
    }
    public void reset() {
        tour.clear();
    }
}