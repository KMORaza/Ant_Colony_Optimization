package Arc_Weighted_l_Cardinality_Tree_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class AntColony {
    private int numAnts;
    private List<Ant> ants;
    private Graph graph;
    public AntColony(int numAnts, Graph graph) {
        this.numAnts = numAnts;
        this.graph = graph;
        this.ants = new ArrayList<>();
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant(graph));
        }
    }
    public void run(int numIterations) {
        for (int iteration = 0; iteration < numIterations; iteration++) {
            for (Ant ant : ants) {
                ant.findTour();
            }
            updatePheromones();
            resetAnts();
        }
    }
    public List<Ant> getAnts() {
        return ants;
    }
    public int getNumAnts() {
        return numAnts;
    }
    private void updatePheromones() {
        for (Ant ant : ants) {
            List<Node> tour = ant.getTour();
            double pheromoneIncrement = calculatePheromoneIncrement(tour);
            for (int i = 0; i < tour.size() - 1; i++) {
                Node source = tour.get(i);
                Node destination = tour.get(i + 1);
                Arc arc = graph.getArc(source, destination);
                arc.setPheromoneLevel(arc.getPheromoneLevel() + pheromoneIncrement);
            }
        }
    }
    public double calculatePheromoneIncrement(List<Node> tour) {
        double tourLength = calculateTourLength(tour);
        return tourLength == 0 ? 1.0 : 1.0 / tourLength;
    }
    public double calculateTourLength(List<Node> tour) {
        double totalLength = 0.0;
        for (int i = 0; i < tour.size() - 1; i++) {
            Node source = tour.get(i);
            Node destination = tour.get(i + 1);
            Arc arc = graph.getArc(source, destination);
            totalLength += arc.getWeight();
        }
        return totalLength;
    }
    private void resetAnts() {
        for (Ant ant : ants) {
            ant.reset();
        }
    }
}