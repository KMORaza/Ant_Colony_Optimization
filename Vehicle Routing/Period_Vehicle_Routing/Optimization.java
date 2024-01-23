package Period_Vehicle_Routing;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
// Created: September 2022
public class Optimization {
    private Graph graph;
    private List<Ant> ants;
    private double[][] pheromones;
    private double[][] distances;
    private double alpha;
    private double beta;
    private double evaporationRate;
    private double Q;
    private int numAnts;
    private int numIterations;
    public Optimization(Graph graph, List<Ant> ants, double alpha, double beta, double evaporationRate, double Q, int numAnts, int numIterations) {
        this.graph = graph;
        this.ants = ants;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;
        this.Q = Q;
        this.numAnts = numAnts;
        this.numIterations = numIterations;
        initialize();
    }
    private void initialize() {
        int numLocations = graph.getLocations().size();
        pheromones = new double[numLocations][numLocations];
        distances = new double[numLocations][numLocations];
        List<Location> locationList = new ArrayList<>(graph.getLocations());
        for (int i = 0; i < numLocations; i++) {
            for (int j = 0; j < numLocations; j++) {
                Location location1 = locationList.get(i);
                Location location2 = locationList.get(j);
                pheromones[i][j] = 1.0;
                distances[i][j] = calculateDistance(location1, location2);
            }
        }
    }
    public void solve() {
        for (int iteration = 0; iteration < numIterations; iteration++) {
            for (Ant ant : ants) {
                constructSolution(ant);
            }
            updatePheromones();
            evaporatePheromones();
            printIterationResults(iteration);
        }
        printFinalResults();
    }
    private void constructSolution(Ant ant) {
        int numLocations = graph.getLocations().size();
        boolean[] visited = ant.getVisited();
        int currentLocation = new Random().nextInt(numLocations);
        ant.visit(currentLocation);
        for (int step = 1; step < numLocations; step++) {
            int nextLocation = selectNextLocation(currentLocation, visited);
            ant.visit(nextLocation);
            currentLocation = nextLocation;
        }
    }
    private int selectNextLocation(int currentLocation, boolean[] visited) {
        int numLocations = graph.getLocations().size();
        int nextLocation;
        do {
            nextLocation = new Random().nextInt(numLocations);
        } while (visited[nextLocation]);

        return nextLocation;
    }
    private void updatePheromones() {
        for (Ant ant : ants) {
            List<Integer> tour = ant.getTour();
            double tourLength = calculateTourLength(tour);
            for (int i = 0; i < tour.size() - 1; i++) {
                int from = tour.get(i);
                int to = tour.get(i + 1);
                pheromones[from][to] += Q / tourLength;
            }
        }
    }
    private void evaporatePheromones() {
        for (int i = 0; i < pheromones.length; i++) {
            for (int j = 0; j < pheromones[i].length; j++) {
                pheromones[i][j] *= (1 - evaporationRate);
            }
        }
    }
    private double calculateDistance(Location location1, Location location2) {
        return Math.sqrt(Math.pow(location1.getX() - location2.getX(), 2) + Math.pow(location1.getY() - location2.getY(), 2));
    }
    private double calculateTourLength(List<Integer> tour) {
        double tourLength = 0.0;
        for (int i = 0; i < tour.size() - 1; i++) {
            int from = tour.get(i);
            int to = tour.get(i + 1);
            tourLength += distances[from][to];
        }
        return tourLength;
    }
    private void printIterationResults(int iteration) {
        System.out.println("Iteration " + iteration + " Results:");
        for (Ant ant : ants) {
            System.out.println("Ant " + ant + " Tour: " + ant.getTour());
        }
        System.out.println();
    }
    private void printFinalResults() {
        System.out.println("Final Results:");
        for (Ant ant : ants) {
            System.out.println("Ant " + ant + " Tour: " + ant.getTour());
        }
        System.out.println();
    }
}