package Split_Delivery_Vehicle_Routing;
// Created: September 2022
import java.util.Arrays;
public class Optimizer {
    private Graph graph;
    private Ant[] ants;
    private double[][] pheromones;
    private double alpha = 1.0;
    private double beta = 2.0;
    private double evaporationRate = 0.5;
    private double Q = 100;
    public Optimizer(Graph graph, int numAnts) {
        this.graph = graph;
        ants = new Ant[numAnts];
        pheromones = new double[graph.getNodes().length][graph.getNodes().length];
        initializePheromones();
        initializeAnts();
    }
    public Graph getGraph() {
        return graph;
    }
    public Ant[] getAnts() {
        return ants;
    }
    private void initializePheromones() {
        for (int i = 0; i < pheromones.length; i++) {
            Arrays.fill(pheromones[i], 1.0); // Initial pheromone level
        }
    }
    private void initializeAnts() {
        for (int i = 0; i < ants.length; i++) {
            ants[i] = new Ant(graph.getNodes().length);
        }
    }
    public void runACO(int maxIterations) {
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            for (Ant ant : ants) {
                ant.clearTour();
                ant.visitNode(0);
                for (int j = 0; j < graph.getNodes().length - 1; j++) {
                    int currentNode = ant.getTour()[j];
                    int nextNode = ant.getRandomNode(currentNode);
                    ant.visitNode(nextNode);
                }
                ant.visitNode(0);
                updatePheromones(ant);
            }
            evaporatePheromones();
        }
    }
    private void updatePheromones(Ant ant) {
        int[] tour = ant.getTour();
        double tourLength = calculateTourLength(tour);
        for (int i = 0; i < tour.length - 1; i++) {
            int currentNode = tour[i];
            int nextNode = tour[i + 1];
            pheromones[currentNode][nextNode] += Q / tourLength;
            pheromones[nextNode][currentNode] += Q / tourLength;
        }
    }
    private void evaporatePheromones() {
        for (int i = 0; i < pheromones.length; i++) {
            for (int j = 0; j < pheromones[i].length; j++) {
                pheromones[i][j] *= (1 - evaporationRate);
            }
        }
    }
    private double calculateTourLength(int[] tour) {
        double tourLength = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            int currentNode = tour[i];
            int nextNode = tour[i + 1];
            tourLength += graph.getDistance(currentNode, nextNode);
        }
        return tourLength;
    }
}