package Weight_Constrained_Graph_Tree_Partition_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class AntColony {
    private static final double EVAPORATION_RATE = 0.1;
    private static final double PHEROMONE_CONSTANT = 1.0;
    private static final int MAX_ITERATIONS = 100;
    private Graph graph;
    private int numAnts;
    private List<Ant> ants;
    private List<Node> globalBestTour;
    private double globalBestScore = Double.MAX_VALUE;
    public AntColony(Graph graph, int numAnts) {
        this.graph = graph;
        this.numAnts = numAnts;
        this.ants = new ArrayList<>();
        initializeAnts();
    }
    private void initializeAnts() {
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant(graph));
        }
    }
    public void solve() {
        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            for (Ant ant : ants) {
                ant.move();
            }
            updatePheromones();
            evaluateSolutions();
            resetAnts();
        }
    }
    private void updatePheromones() {
        System.out.println("Updating pheromones...");
        for (Node node : graph.getNodes().values()) {
            for (Node neighbor : node.getNeighbors()) {
                double pheromone = (1.0 - EVAPORATION_RATE) * node.getPheromone(neighbor)
                        + PHEROMONE_CONSTANT / antTourLength(globalBestTour); // Global best tour length
                node.setPheromone(neighbor, pheromone);
            }
        }
    }
    private void evaluateSolutions() {
        for (Ant ant : ants) {
            double tourLength = antTourLength(ant.getTour());
            if (tourLength < globalBestScore) {
                globalBestTour = new ArrayList<>(ant.getTour());
                globalBestScore = tourLength;
            }
        }
    }
    private double antTourLength(List<Node> tour) {
        double length = 0.0;
        for (int i = 0; i < tour.size() - 1; i++) {
            Node current = tour.get(i);
            Node next = tour.get(i + 1);
            length += current.getDistanceTo(next);
        }
        return length;
    }
    private void resetAnts() {
        for (Ant ant : ants) {
            ant.reset();
        }
    }
    public List<Node> getGlobalBestTour() {
        return globalBestTour;
    }
    public double getGlobalBestScore() {
        return globalBestScore;
    }
}